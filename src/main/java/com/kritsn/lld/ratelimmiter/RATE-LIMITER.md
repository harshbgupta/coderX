# LLD: RATE LIMITER SYSTEM

---

## SECTION 1: CLARIFYING QUESTIONS

### Questions to Ask Interviewer

**SCALE & CONFIGURATION:**
- Per user or per API endpoint?
- How many requests allowed per window? (1K? 10K?)
- Time window? (per second? per minute? per hour?)
- Single server or distributed (multiple servers)?

**ALGORITHM:**
- Which algorithm? (Token Bucket, Sliding Window, Leaky Bucket, Fixed Window)
- Burst allowed or strict limit?
- Soft limit (warn) or hard limit (reject)?

**BEHAVIOR:**
- What happens when limit exceeded? (queue, reject, or delay)
- Retry-After header needed?
- Track across distributed systems?

**EDGE CASES:**
- What if time sync issues across servers?
- Expired tokens handling?
- Multiple regions support?

---

## SECTION 2: CLASS & INTERFACE NAMES

### Interfaces (2)
- `RateLimitingStrategy` - Contract for different algorithms
- `RequestProcessor` - Contract for processing requests

### Classes (10)
- `RateLimiter` - Main orchestrator
- `RateLimitConfig` - Configuration (requests, window, etc.)
- `User` - Represents a user/client
- `Request` - Represents an API request
- `TimeWindow` - Time window management
- `TokenBucket` - Token bucket algorithm (Strategy)
- `SlidingWindow` - Sliding window algorithm (Strategy)
- `LeakyBucket` - Leaky bucket algorithm (Strategy)
- `FixedWindow` - Fixed window algorithm (Strategy)
- `RateLimitResponse` - Response with status

### Exceptions (3)
- `RateLimitExceededException` - When limit exceeded
- `InvalidConfigException` - Invalid configuration
- `RequestRejectedException` - Request rejected

---

## SECTION 3: CLASS STRUCTURE

### RateLimitConfig

```
RateLimitConfig

Fields:
  - maxRequests: int (requests per window)
  - windowSize: long (in milliseconds)
  - algorithm: String (TOKEN_BUCKET, SLIDING_WINDOW, etc.)

Constructor:
  + RateLimitConfig(maxRequests: int, windowSize: long, algorithm: String)

Methods:
  + getMaxRequests(): int
  + getWindowSize(): long
  + getAlgorithm(): String
  + isValid(): boolean

Purpose:
  • Immutable configuration object
  • Validates configuration on creation
```

---

### User

```
User

Fields:
  - userId: String
  - createdAt: long

Constructor:
  + User(userId: String)

Methods:
  + getUserId(): String
  + getCreatedAt(): long

Purpose:
  • Represents a user/client
  • Tracked for rate limiting
```

---

### Request

```
Request

Fields:
  - requestId: String
  - userId: String
  - timestamp: long
  - endpoint: String

Constructor:
  + Request(requestId: String, userId: String, endpoint: String)

Methods:
  + getRequestId(): String
  + getUserId(): String
  + getTimestamp(): long
  + getEndpoint(): String

Purpose:
  • Represents an API request
  • Tracks when request was made
```

---

### RateLimitResponse

```
RateLimitResponse

Fields:
  - isAllowed: boolean
  - tokensRemaining: int
  - retryAfterMs: long (milliseconds to wait)
  - message: String

Constructor:
  + RateLimitResponse(isAllowed: boolean, tokensRemaining: int, retryAfterMs: long)

Methods:
  + isAllowed(): boolean
  + getTokensRemaining(): int
  + getRetryAfterMs(): long
  + getMessage(): String

Purpose:
  • Response from rate limiter
  • Tell caller if request allowed
  • Tell when to retry if denied
```

---

### TimeWindow

```
TimeWindow

Fields:
  - startTime: long
  - endTime: long
  - windowSize: long

Constructor:
  + TimeWindow(windowSize: long)

Methods:
  + isExpired(): boolean
  + getTimeRemaining(): long
  + reset(): void

Purpose:
  • Manages time window boundaries
  • Checks if window expired
```

---

### RateLimitingStrategy (Interface)

```
<<interface>> RateLimitingStrategy

Purpose:
  • Strategy Pattern - Why: Different algorithms (Token Bucket, Sliding Window, etc.)
  • Benefit: Easy to switch algorithms without changing RateLimiter
  • Trade-off: More classes, but cleaner design

Methods:
  + allowRequest(userId: String, timestamp: long): RateLimitResponse
    └─ Check if request allowed under this algorithm
  
  + reset(userId: String): void
    └─ Reset state for user
```

---

### TokenBucket (Strategy)

```
TokenBucket implements RateLimitingStrategy

Fields:
  - userTokens: Map<String, Double> (tokens available)
  - lastRefillTime: Map<String, Long>
  - maxTokens: double
  - refillRate: double (tokens per second)

Methods:
  + allowRequest(userId: String, timestamp: long): RateLimitResponse
    Logic:
      1. Calculate elapsed time since last refill
      2. Add tokens: elapsed * refillRate
      3. Cap at maxTokens
      4. If tokens >= 1: consume 1 token, allow request
      5. Else: deny request, tell when retry
  
  + reset(userId: String): void

Purpose:
  • Token Bucket Algorithm - Why: Handles bursts well
  • Benefit: Allows traffic spikes (buckets up tokens)
  • Trade-off: More complex calculation
```

---

### SlidingWindow (Strategy)

```
SlidingWindow implements RateLimitingStrategy

Fields:
  - userRequests: Map<String, Queue<Long>> (timestamps of requests)
  - maxRequests: int
  - windowSize: long (milliseconds)

Methods:
  + allowRequest(userId: String, timestamp: long): RateLimitResponse
    Logic:
      1. Get queue of request times for user
      2. Remove expired requests (older than windowSize)
      3. If queue.size() < maxRequests: add request, allow
      4. Else: deny request

  + reset(userId: String): void

Purpose:
  • Sliding Window Algorithm - Why: Most accurate
  • Benefit: Precise rate limiting, no bursts
  • Trade-off: Memory intensive (stores timestamps)
```

---

### LeakyBucket (Strategy)

```
LeakyBucket implements RateLimitingStrategy

Fields:
  - userBuckets: Map<String, Integer> (current size)
  - maxCapacity: int
  - leakRate: double (requests/second)
  - lastLeakTime: Map<String, Long>

Methods:
  + allowRequest(userId: String, timestamp: long): RateLimitResponse
    Logic:
      1. Calculate leaked requests since last time
      2. Reduce bucket size by leaked amount
      3. If bucket.size() < maxCapacity: add request, allow
      4. Else: deny (bucket full)

  + reset(userId: String): void

Purpose:
  • Leaky Bucket Algorithm - Why: Smooth traffic flow
  • Benefit: Prevents traffic bursts, predictable
  • Trade-off: Rejects requests after capacity
```

---

### FixedWindow (Strategy)

```
FixedWindow implements RateLimitingStrategy

Fields:
  - userWindowStart: Map<String, Long>
  - userCount: Map<String, Integer>
  - maxRequests: int
  - windowSize: long

Methods:
  + allowRequest(userId: String, timestamp: long): RateLimitResponse
    Logic:
      1. Check if current window expired
      2. If expired: reset count and start new window
      3. If count < maxRequests: increment count, allow
      4. Else: deny (window limit reached)

  + reset(userId: String): void

Purpose:
  • Fixed Window Algorithm - Why: Simplest
  • Benefit: Low memory, easy to implement
  • Trade-off: Allows burst at window boundaries
```

---

### RateLimiter

```
RateLimiter

Fields:
  - config: RateLimitConfig
  - strategy: RateLimitingStrategy (Strategy Pattern)
  - users: Map<String, User>

Constructor:
  + RateLimiter(config: RateLimitConfig)
    Initialize strategy based on config algorithm
    Why: Strategy Pattern for flexibility
    Benefit: Swap algorithms without changing RateLimiter
    Trade-off: Extra abstraction layer

Methods:
  + allowRequest(userId: String, endpoint: String): RateLimitResponse (synchronized)
    Logic:
      1. Register user if not exists
      2. Get current timestamp
      3. Call strategy.allowRequest(userId, timestamp)
      4. Return response
  
  + resetUser(userId: String): void
    └─ Reset rate limit for specific user
  
  + getStatus(userId: String): String
    └─ Print current status for user

Purpose:
  • Main orchestrator
  • Delegates to strategy (algorithm)
  • Thread-safe request handling
  • Why: Separation of concerns
  • Benefit: Easy to test, easy to extend
```

---

## SECTION 4: IMPLEMENTATION

### RateLimitConfig

```java
class RateLimitConfig {
    private int maxRequests;
    private long windowSize; // milliseconds
    private String algorithm; // TOKEN_BUCKET, SLIDING_WINDOW, etc.
    
    public RateLimitConfig(int maxRequests, long windowSize, String algorithm) {
        this.maxRequests = maxRequests;
        this.windowSize = windowSize;
        this.algorithm = algorithm;
    }
    
    public boolean isValid() {
        return maxRequests > 0 && windowSize > 0 && algorithm != null;
    }
    
    public int getMaxRequests() { return maxRequests; }
    public long getWindowSize() { return windowSize; }
    public String getAlgorithm() { return algorithm; }
}
```

---

### User

```java
class User {
    private String userId;
    private long createdAt;
    
    public User(String userId) {
        this.userId = userId;
        this.createdAt = System.currentTimeMillis();
    }
    
    public String getUserId() { return userId; }
    public long getCreatedAt() { return createdAt; }
}
```

---

### Request

```java
class Request {
    private String requestId;
    private String userId;
    private long timestamp;
    private String endpoint;
    
    public Request(String requestId, String userId, String endpoint) {
        this.requestId = requestId;
        this.userId = userId;
        this.endpoint = endpoint;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getRequestId() { return requestId; }
    public String getUserId() { return userId; }
    public long getTimestamp() { return timestamp; }
    public String getEndpoint() { return endpoint; }
}
```

---

### RateLimitResponse

```java
class RateLimitResponse {
    private boolean isAllowed;
    private int tokensRemaining;
    private long retryAfterMs;
    private String message;
    
    public RateLimitResponse(boolean isAllowed, int tokensRemaining, 
                           long retryAfterMs) {
        this.isAllowed = isAllowed;
        this.tokensRemaining = tokensRemaining;
        this.retryAfterMs = retryAfterMs;
        this.message = isAllowed ? 
                      "Request allowed" : 
                      "Rate limit exceeded";
    }
    
    public boolean isAllowed() { return isAllowed; }
    public int getTokensRemaining() { return tokensRemaining; }
    public long getRetryAfterMs() { return retryAfterMs; }
    public String getMessage() { return message; }
}
```

---

### TimeWindow

```java
class TimeWindow {
    private long startTime;
    private long windowSize;
    
    public TimeWindow(long windowSize) {
        this.windowSize = windowSize;
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * Check if current window has expired
     */
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) >= windowSize;
    }
    
    /**
     * Get time remaining in current window
     */
    public long getTimeRemaining() {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - startTime;
        return Math.max(0, windowSize - elapsed);
    }
    
    /**
     * Reset window (start new one)
     */
    public void reset() {
        startTime = System.currentTimeMillis();
    }
    
    public long getWindowSize() { return windowSize; }
}
```

---

### RateLimitingStrategy (Interface)

```java
interface RateLimitingStrategy {
    /**
     * Strategy Pattern - Why: Different algorithms
     * Benefit: Easy to swap FCFS, SCAN, LOOK without changing RateLimiter
     * Trade-off: More classes, but cleaner design
     */
    RateLimitResponse allowRequest(String userId, long timestamp);
    void reset(String userId);
}
```

---

### TokenBucket Implementation

```java
class TokenBucket implements RateLimitingStrategy {
    private Map<String, Double> userTokens;
    private Map<String, Long> lastRefillTime;
    private double maxTokens;
    private double refillRate; // tokens per second
    
    public TokenBucket(int maxRequests, long windowSizeMs) {
        this.userTokens = new ConcurrentHashMap<>();
        this.lastRefillTime = new ConcurrentHashMap<>();
        this.maxTokens = maxRequests;
        // If 10 requests per 1000ms → 10 tokens per second
        this.refillRate = (double) maxRequests / (windowSizeMs / 1000.0);
    }
    
    /**
     * Token Bucket Algorithm
     * 
     * Logic:
     *   1. Calculate elapsed time since last refill
     *   2. Add tokens: elapsed * refillRate
     *   3. Cap at maxTokens (can't exceed max)
     *   4. If tokens >= 1: consume 1 token, allow request
     *   5. Else: deny request, tell when retry
     * 
     * Why: Handles bursts well (tokens accumulate)
     * Benefit: Allows traffic spikes
     * Trade-off: More complex calculation
     */
    @Override
    public synchronized RateLimitResponse allowRequest(String userId, 
                                                       long timestamp) {
        // Initialize user if first request
        if (!userTokens.containsKey(userId)) {
            userTokens.put(userId, maxTokens);
            lastRefillTime.put(userId, timestamp);
        }
        
        // Calculate elapsed time since last refill
        long lastTime = lastRefillTime.get(userId);
        long elapsedMs = timestamp - lastTime;
        double elapsedSeconds = elapsedMs / 1000.0;
        
        // Add tokens based on elapsed time
        double tokens = userTokens.get(userId);
        tokens += elapsedSeconds * refillRate;
        tokens = Math.min(tokens, maxTokens); // Cap at max
        
        // Check if can process request
        if (tokens >= 1.0) {
            tokens -= 1.0; // Consume 1 token
            userTokens.put(userId, tokens);
            lastRefillTime.put(userId, timestamp);
            
            return new RateLimitResponse(true, (int)tokens, 0);
        } else {
            // Calculate retry time
            double tokensNeeded = 1.0 - tokens;
            long retryAfterMs = (long) (tokensNeeded / refillRate * 1000);
            
            return new RateLimitResponse(false, 0, retryAfterMs);
        }
    }
    
    @Override
    public synchronized void reset(String userId) {
        userTokens.put(userId, maxTokens);
        lastRefillTime.put(userId, System.currentTimeMillis());
    }
}
```

---

### SlidingWindow Implementation

```java
class SlidingWindow implements RateLimitingStrategy {
    private Map<String, Queue<Long>> userRequests;
    private int maxRequests;
    private long windowSizeMs;
    
    public SlidingWindow(int maxRequests, long windowSizeMs) {
        this.userRequests = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }
    
    /**
     * Sliding Window Algorithm
     * 
     * Logic:
     *   1. Get queue of request timestamps for user
     *   2. Remove expired requests (older than windowSize)
     *   3. If queue.size() < maxRequests: add request, allow
     *   4. Else: deny request
     * 
     * Why: Most accurate rate limiting
     * Benefit: Precise boundaries, no burst anomalies
     * Trade-off: Memory intensive (stores all timestamps)
     */
    @Override
    public synchronized RateLimitResponse allowRequest(String userId, 
                                                       long timestamp) {
        // Initialize user if first request
        userRequests.putIfAbsent(userId, new LinkedList<>());
        
        Queue<Long> requests = userRequests.get(userId);
        long windowStart = timestamp - windowSizeMs;
        
        // Remove expired requests (older than window)
        while (!requests.isEmpty() && requests.peek() < windowStart) {
            requests.poll();
        }
        
        // Check if can add request
        if (requests.size() < maxRequests) {
            requests.add(timestamp);
            
            int tokensRemaining = maxRequests - requests.size();
            return new RateLimitResponse(true, tokensRemaining, 0);
        } else {
            // Tell when oldest request expires
            long oldestRequestTime = requests.peek();
            long retryAfterMs = (oldestRequestTime + windowSizeMs) - timestamp;
            
            return new RateLimitResponse(false, 0, retryAfterMs);
        }
    }
    
    @Override
    public synchronized void reset(String userId) {
        userRequests.put(userId, new LinkedList<>());
    }
}
```

---

### FixedWindow Implementation

```java
class FixedWindow implements RateLimitingStrategy {
    private Map<String, Long> userWindowStart;
    private Map<String, Integer> userCount;
    private int maxRequests;
    private long windowSizeMs;
    
    public FixedWindow(int maxRequests, long windowSizeMs) {
        this.userWindowStart = new ConcurrentHashMap<>();
        this.userCount = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }
    
    /**
     * Fixed Window Algorithm
     * 
     * Logic:
     *   1. Check if current window expired
     *   2. If expired: reset count and start new window
     *   3. If count < maxRequests: increment count, allow
     *   4. Else: deny (window limit reached)
     * 
     * Why: Simplest algorithm
     * Benefit: Low memory, fast calculation
     * Trade-off: Allows burst at window boundaries (e.g., 59s and 01s)
     */
    @Override
    public synchronized RateLimitResponse allowRequest(String userId, 
                                                       long timestamp) {
        // Initialize user if first request
        if (!userWindowStart.containsKey(userId)) {
            userWindowStart.put(userId, timestamp);
            userCount.put(userId, 0);
        }
        
        long windowStart = userWindowStart.get(userId);
        long windowEnd = windowStart + windowSizeMs;
        
        // Check if window expired (start new one)
        if (timestamp >= windowEnd) {
            userWindowStart.put(userId, timestamp);
            userCount.put(userId, 0);
        }
        
        // Check if can process request
        int count = userCount.get(userId);
        if (count < maxRequests) {
            count++;
            userCount.put(userId, count);
            
            int tokensRemaining = maxRequests - count;
            return new RateLimitResponse(true, tokensRemaining, 0);
        } else {
            // Tell when current window ends
            long retryAfterMs = windowEnd - timestamp;
            
            return new RateLimitResponse(false, 0, retryAfterMs);
        }
    }
    
    @Override
    public synchronized void reset(String userId) {
        userWindowStart.put(userId, System.currentTimeMillis());
        userCount.put(userId, 0);
    }
}
```

---

### RateLimiter

```java
class RateLimiter {
    private RateLimitConfig config;
    private RateLimitingStrategy strategy;
    private Map<String, User> users;
    
    public RateLimiter(RateLimitConfig config) throws InvalidConfigException {
        if (!config.isValid()) {
            throw new InvalidConfigException("Invalid rate limit config");
        }
        
        this.config = config;
        this.users = new ConcurrentHashMap<>();
        
        // Strategy Pattern - Why: Different algorithms
        // Benefit: Swap without changing RateLimiter
        // Trade-off: Extra abstraction layer
        this.strategy = createStrategy(config.getAlgorithm(), config);
        
        System.out.println("[RATE LIMITER] Initialized with " + 
                         config.getAlgorithm() + " algorithm");
    }
    
    /**
     * Create strategy based on algorithm type
     */
    private RateLimitingStrategy createStrategy(String algorithm, 
                                               RateLimitConfig config) {
        switch (algorithm) {
            case "TOKEN_BUCKET":
                return new TokenBucket(config.getMaxRequests(), 
                                      config.getWindowSize());
            case "SLIDING_WINDOW":
                return new SlidingWindow(config.getMaxRequests(), 
                                        config.getWindowSize());
            case "FIXED_WINDOW":
                return new FixedWindow(config.getMaxRequests(), 
                                      config.getWindowSize());
            default:
                return new TokenBucket(config.getMaxRequests(), 
                                      config.getWindowSize());
        }
    }
    
    /**
     * Check if request allowed
     * 
     * Flow:
     *   1. Register user if new
     *   2. Delegate to strategy
     *   3. Return response
     */
    public synchronized RateLimitResponse allowRequest(String userId, 
                                                       String endpoint) {
        // Register user if not exists
        users.putIfAbsent(userId, new User(userId));
        
        long currentTime = System.currentTimeMillis();
        
        // Delegate to strategy
        RateLimitResponse response = strategy.allowRequest(userId, currentTime);
        
        if (response.isAllowed()) {
            System.out.println("[ALLOWED] User: " + userId + " Endpoint: " + 
                             endpoint + " Tokens: " + 
                             response.getTokensRemaining());
        } else {
            System.out.println("[DENIED] User: " + userId + " Endpoint: " + 
                             endpoint + " Retry after: " + 
                             response.getRetryAfterMs() + "ms");
        }
        
        return response;
    }
    
    /**
     * Reset user's rate limit
     */
    public void resetUser(String userId) {
        strategy.reset(userId);
        System.out.println("[RESET] User: " + userId);
    }
    
    /**
     * Get current status
     */
    public void getStatus(String userId) {
        System.out.println("[STATUS] User: " + userId + " exists: " + 
                         users.containsKey(userId));
    }
}
```

---

### Exceptions

```java
class RateLimitExceededException extends Exception {
    public RateLimitExceededException(String msg) { super(msg); }
}

class InvalidConfigException extends Exception {
    public InvalidConfigException(String msg) { super(msg); }
}

class RequestRejectedException extends Exception {
    public RequestRejectedException(String msg) { super(msg); }
}
```

---

## SECTION 5: MAIN METHOD (Demo & Flow)

### Setup & Initialization

```java
public class RateLimiterDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("========== RATE LIMITER DEMO ==========\n");
        
        // Create config: 5 requests per 1000ms (5 req/sec)
        RateLimitConfig config = new RateLimitConfig(5, 1000, "TOKEN_BUCKET");
        RateLimiter limiter = new RateLimiter(config);
```

---

### Scenario 1: Requests Within Limit

```
INPUT:
  User "alice" makes 5 requests
  All within time window

PROCESS:
  Request 1: allowRequest("alice", "/api/data")
  Request 2: allowRequest("alice", "/api/data")
  Request 3: allowRequest("alice", "/api/data")
  Request 4: allowRequest("alice", "/api/data")
  Request 5: allowRequest("alice", "/api/data")

OUTPUT:
[ALLOWED] User: alice Endpoint: /api/data Tokens: 4
[ALLOWED] User: alice Endpoint: /api/data Tokens: 3
[ALLOWED] User: alice Endpoint: /api/data Tokens: 2
[ALLOWED] User: alice Endpoint: /api/data Tokens: 1
[ALLOWED] User: alice Endpoint: /api/data Tokens: 0
```

---

### Scenario 2: Requests Exceeding Limit

```
INPUT:
  User "alice" makes 6th request immediately

PROCESS:
  Request 6: allowRequest("alice", "/api/data")
  Tokens available: 0
  Cannot process

OUTPUT:
[DENIED] User: alice Endpoint: /api/data Retry after: 200ms

Client should wait 200ms before retrying
```

---

### Scenario 3: Multiple Users

```
INPUT:
  User "alice": 3 requests
  User "bob": 4 requests
  Same time window

PROCESS:
  alice req 1-3: Allowed
  bob req 1-4: Allowed
  alice req 4: Allowed
  bob req 5: Denied (bob exceeded limit)

OUTPUT:
[ALLOWED] User: alice... Tokens: 4
[ALLOWED] User: alice... Tokens: 3
[ALLOWED] User: alice... Tokens: 2
[ALLOWED] User: bob... Tokens: 4
[ALLOWED] User: bob... Tokens: 3
[ALLOWED] User: bob... Tokens: 2
[ALLOWED] User: bob... Tokens: 1
[ALLOWED] User: alice... Tokens: 1
[DENIED] User: bob... Retry after: 150ms
```

---

### Scenario 4: Tokens Replenish Over Time

```
INPUT:
  User "alice": 5 requests
  Wait 500ms
  User "alice": 2 more requests

PROCESS:
  Request 1-5: Allowed (tokens 5→0)
  Wait 500ms: Tokens accumulate (refillRate = 5/sec)
             New tokens = 500ms * 5req/sec = 2.5 tokens
  Request 6: Allowed (2.5 - 1 = 1.5 tokens)
  Request 7: Allowed (1.5 - 1 = 0.5 tokens)

OUTPUT:
[ALLOWED] First 5 requests
[WAIT 500ms]
[ALLOWED] Request 6 with 1 token remaining
[ALLOWED] Request 7 with 0 tokens remaining
```

---

### Scenario 5: Algorithm Comparison

```
INPUT: 10 requests in 1 second (5 req/sec limit)
       At timestamps: 0, 100, 200, 300, 400, 500, 600, 700, 800, 900ms

FIXED_WINDOW (0-1000ms window):
  Requests 1-5: Allowed
  Requests 6-10: Denied (limit reached)
  ❌ Burst at boundary (another 5 allowed at 1000ms)

TOKEN_BUCKET:
  Requests 1-5: Allowed immediately
  Requests 6-10: Denied but can retry in ~200ms
  ✓ Smoother rate

SLIDING_WINDOW:
  Requests 1-5: Allowed (within window)
  Request 6: Denied (oldest at 0ms about to expire)
  ✓ Most accurate

TOKEN_BUCKET WINNER: Best balance of simplicity and smoothness
```

---

## SECTION 6: REQUIREMENTS

### Functional Requirements ✅

| Requirement | Description |
|---|---|
| Check Request Allowed | Determine if user request allowed |
| Count Requests | Track requests per user |
| Time Windows | Respect time windows (second, minute, hour) |
| Multiple Users | Isolate limits per user |
| Retry Information | Tell when to retry if denied |

### Out of Scope ❌

| Item | Reason |
|---|---|
| Distributed tracking | MVP is single server |
| Persistent storage | No database needed |
| Custom penalties | Simple allow/deny only |
| Real-time analytics | No reporting needed |
| UI/Dashboard | No monitoring UI |

### Non-Functional Requirements ✅

| Requirement | Target |
|---|---|
| Performance | < 1ms per request check |
| Scalability | 10K+ users concurrent |
| Accuracy | Precise rate limiting (no false positives) |
| Low Memory | Minimal overhead per user |

### Out of Scope ❌

| Item | Reason |
|---|---|
| Sub-millisecond latency | 1ms acceptable |
| Distributed consensus | Single server MVP |
| Fault tolerance | Not critical for MVP |
| 99.99% uptime | 99% acceptable |

---

## SECTION 7: SCALABILITY & DESIGN

### Why This Design is Better

✅ **Separation of Concerns**
- RateLimiter: Orchestration only
- Strategy: Algorithm implementation
- Config: Configuration management

✅ **Strategy Pattern for Algorithms**
- FCFS, TOKEN_BUCKET, SLIDING_WINDOW implementations easy to add
- Algorithm changes don't affect core logic
- Easy to test different algorithms

✅ **Thread-Safe Operations**
- Synchronized methods prevent race conditions
- ConcurrentHashMap for concurrent access
- Atomic updates

✅ **Flexible Configuration**
- Algorithm pluggable at runtime
- Easy to switch algorithms
- Multiple instances with different strategies

✅ **Accurate Rate Limiting**
- Per-user tracking
- Time-based windows
- Precise token/request counting

---

### How to Scale to 10K+ Users & Multiple Regions

#### Layer 1: Distributed Rate Limiting

```
Strategy: Each server has local cache
  • Fast lookup (no network call)
  • Eventually consistent across servers
  • Sync via Redis for consistency

Benefit: Reduced latency, increased throughput
```

#### Layer 2: Redis-Backed Storage

```
Strategy: Use Redis for distributed state
  • Users: redis.incr("rate_limit:" + userId)
  • TTL: Auto-expire after window
  • Atomic operations: INCR, GETEX

Benefit: Multi-server consistency
```

#### Layer 3: Hierarchical Limits

```
Strategy: Multiple limits per user
  • Per-second limit: 100 req/sec
  • Per-minute limit: 5000 req/min
  • Per-hour limit: 100K req/hour

Benefit: Control burst and long-term usage
```

#### Layer 4: Priority Queuing

```
Strategy: Different limits for different tiers
  • Free tier: 100 req/min
  • Premium tier: 10K req/min
  • Enterprise tier: Unlimited

Benefit: Monetization, resource optimization
```

#### Layer 5: Adaptive Limits

```
Strategy: Adjust limits based on system load
  • High load: Lower limits
  • Low load: Higher limits
  • Graceful degradation

Benefit: System stability, fairness
```

---

### Design Patterns Used

#### 1. Strategy Pattern (RateLimitingStrategy)

```
What: TOKEN_BUCKET, SLIDING_WINDOW, FIXED_WINDOW algorithms

Why: Different algorithms have different trade-offs
     Can swap without changing core logic

Benefit:
  • Open/Closed Principle
  • Easy to add new algorithms
  • Easy to test each algorithm

Trade-off:
  • More classes (3-4 instead of 1)
  • Extra method calls
```

#### 2. Factory Pattern (createStrategy)

```
What: Factory method for creating strategies

Why: Encapsulate strategy instantiation
     Centralized creation logic

Benefit:
  • Single point of change
  • Easy to add new strategies
```

#### 3. Map-Based Storage (ConcurrentHashMap)

```
What: Per-user tracking using maps

Why: Fast O(1) lookup
     Thread-safe concurrent access

Benefit:
  • No locks needed (concurrent)
  • Scales to 10K+ users
```

#### 4. Enum for Configuration

```
What: Algorithm types as enums (or strings)

Why: Type safety
     Prevents invalid values

Benefit:
  • Compile-time checking
  • Self-documenting
```

---

### Time Complexity

| Operation | Time | Reason |
|---|---|---|
| Check Request | O(1) | Direct map lookup |
| Add Token | O(1) | Simple arithmetic |
| Remove Expired | O(n) | n = requests in window (Sliding Window only) |
| Reset User | O(1) | Direct map update |
| Get Status | O(1) | Map lookup |

**Note:** Sliding Window has O(n) due to removing expired timestamps, others are O(1)

---

### Space Complexity

| Component | Space | Notes |
|---|---|---|
| Per-User Storage | O(u) | u = number of users |
| Sliding Window | O(u*r) | r = requests in window |
| Token Bucket | O(u) | Only timestamp + token count |
| Fixed Window | O(u) | Only timestamp + counter |
| **Total** | **O(u*r) worst case** | **Manageable** |

---

## SECTION 8: FOLLOW-UP QUESTIONS & ANSWERS

### Q1: Which algorithm is best and why?

**Answer:**

No single "best" - depends on use case:

**Fixed Window:**
- Pros: Simplest, lowest memory
- Cons: Allows burst at boundaries
- Use: Simple APIs, less critical

**Token Bucket:**
- Pros: Handles bursts well, smooth
- Cons: Slightly complex
- Use: Most applications (RECOMMENDED)

**Sliding Window:**
- Pros: Most accurate, no boundary issues
- Cons: Memory intensive, complex
- Use: Strict SLA requirements

**Leaky Bucket:**
- Pros: Smoothest traffic
- Cons: Rejects after capacity
- Use: Queue-based systems

**Winner: TOKEN_BUCKET** - Best balance of simplicity and fairness

---

### Q2: How do you handle clock skew in distributed systems?

**Answer:**

Problem: Servers have different times

**Solution 1: NTP Sync**
```
• Keep servers time-synchronized
• Use NTP protocol
• Tolerate small drift (< 1 second)
```

**Solution 2: Leeway Window**
```
• Allow small time differences
• Example: Accept clock drift up to 5 seconds
• Trade off: Slight over-limit tolerance
```

**Solution 3: Redis Timestamps**
```
• Use server time for window boundaries
• Not client time
• Reduces clock skew impact
```

---

### Q3: What if user hammers the API with requests?

**Answer:**

Token bucket handles this gracefully:

```
Example: 5 req/sec limit, user sends 100 requests at once

Time 0ms: Tokens available = 5
  Request 1-5: ALLOWED (tokens consumed)
  Request 6-100: DENIED (no tokens, retry ~200ms)

Time 200ms: Tokens refilled = 1 more
  Request 6: ALLOWED
  Request 7-100: DENIED (retry ~200ms more)

Result: Smooth rate limiting, no crash
```

---

### Q4: How do you reset a user's limit?

**Answer:**

Call reset method:

```java
limiter.resetUser("alice");
// Sets tokens to max or clears queue
```

**When to reset:**
- User paid for upgrade
- Admin wants to clear limit
- Period-based reset (daily, weekly)
- Automatic reset at window end

---

### Q5: What about burst allowance?

**Answer:**

Token Bucket naturally allows bursts:

```
Example: 5 req/sec limit, but 10 requests burst is OK

Tokens accumulate over 2 seconds (5 * 2 = 10 tokens)
User makes 10 requests suddenly
All allowed because 10 tokens available
Then must wait for tokens to refill

This is feature, not bug:
• Allows traffic flexibility
• No artificial smoothing
• Fair over time
```

---

### Q6: How would you add different tiers?

**Answer:**

Multiple RateLimiter instances:

```java
// Free tier: 100 req/min
RateLimitConfig freeTier = new RateLimitConfig(100, 60000, "TOKEN_BUCKET");
RateLimiter freeLimiter = new RateLimiter(freeTier);

// Premium tier: 10K req/min
RateLimitConfig premiumTier = new RateLimitConfig(10000, 60000, "TOKEN_BUCKET");
RateLimiter premiumLimiter = new RateLimiter(premiumTier);

// In API:
if (userTier == PREMIUM) {
    response = premiumLimiter.allowRequest(userId, endpoint);
} else {
    response = freeLimiter.allowRequest(userId, endpoint);
}
```

---

### Q7: How do you test rate limiter?

**Answer:**

Unit tests for each algorithm:

```java
@Test
public void testTokenBucketBasic() {
    RateLimitConfig config = new RateLimitConfig(5, 1000, "TOKEN_BUCKET");
    RateLimiter limiter = new RateLimiter(config);
    
    // First 5 requests allowed
    for (int i = 0; i < 5; i++) {
        assertTrue(limiter.allowRequest("alice", "/api").isAllowed());
    }
    
    // 6th request denied
    assertFalse(limiter.allowRequest("alice", "/api").isAllowed());
}

@Test
public void testTokenRefill() throws InterruptedException {
    RateLimitConfig config = new RateLimitConfig(5, 1000, "TOKEN_BUCKET");
    RateLimiter limiter = new RateLimiter(config);
    
    // Use 5 tokens
    for (int i = 0; i < 5; i++) {
        limiter.allowRequest("alice", "/api");
    }
    
    // Wait 500ms (tokens accumulate)
    Thread.sleep(500);
    
    // Can make ~2-3 more requests (2.5 tokens accumulated)
    assertTrue(limiter.allowRequest("alice", "/api").isAllowed());
    assertTrue(limiter.allowRequest("alice", "/api").isAllowed());
}

@Test
public void testMultipleUsers() {
    RateLimitConfig config = new RateLimitConfig(2, 1000, "FIXED_WINDOW");
    RateLimiter limiter = new RateLimiter(config);
    
    // alice uses 2
    assertTrue(limiter.allowRequest("alice", "/api").isAllowed());
    assertTrue(limiter.allowRequest("alice", "/api").isAllowed());
    assertFalse(limiter.allowRequest("alice", "/api").isAllowed());
    
    // bob still has 2 (isolated)
    assertTrue(limiter.allowRequest("bob", "/api").isAllowed());
    assertTrue(limiter.allowRequest("bob", "/api").isAllowed());
    assertFalse(limiter.allowRequest("bob", "/api").isAllowed());
}
```

---

### Q8: How would you implement per-endpoint limits?

**Answer:**

Track per (user, endpoint) combination:

```java
class RateLimiter {
    private Map<String, RateLimitingStrategy> endpointStrategies;
    
    public RateLimitResponse allowRequest(String userId, String endpoint) {
        String key = userId + ":" + endpoint;
        
        // Create strategy for endpoint if not exists
        endpointStrategies.putIfAbsent(key, createStrategy());
        
        RateLimitingStrategy strategy = endpointStrategies.get(key);
        return strategy.allowRequest(userId, System.currentTimeMillis());
    }
}

// Different limits per endpoint:
// POST /api/send: 10 req/sec (expensive)
// GET /api/list: 100 req/sec (cheap)
```

---

### Q9: How do you handle Redis failures?

**Answer:**

Graceful degradation:

```java
public RateLimitResponse allowRequest(String userId, String endpoint) {
    try {
        // Try Redis first
        return redisLimiter.allowRequest(userId, endpoint);
    } catch (RedisException e) {
        // Fall back to local limiter
        System.out.println("[FALLBACK] Using local rate limiter");
        return localLimiter.allowRequest(userId, endpoint);
    }
}
```

**Benefits:**
- System still works without Redis
- No cascading failures
- Automatic recovery when Redis back

---

### Q10: What design patterns did you use and why?

**Answer:**

Four key patterns:

| Pattern | Where | Why |
|---|---|---|
| **Strategy** | RateLimitingStrategy | Swap algorithms easily |
| **Factory** | createStrategy() | Encapsulate creation |
| **Map-based** | ConcurrentHashMap | Fast O(1) lookup |
| **Enum** | Algorithm types | Type safety |

**Detailed:**

1. **Strategy (RateLimitingStrategy)**
    - Why: Different algorithms (FCFS, TOKEN_BUCKET, SLIDING_WINDOW)
    - Benefit: Easy to add new algorithm
    - Trade-off: More classes
    - Interview: "I used Strategy because rate limiters can use different algorithms"

2. **Factory (createStrategy)**
    - Why: Centralized strategy creation
    - Benefit: Single point of change
    - Trade-off: Extra method

3. **Map-based Storage (ConcurrentHashMap)**
    - Why: O(1) per-user lookup
    - Benefit: Scales to 10K+ users
    - Trade-off: Memory usage

4. **Thread Safety (Synchronized)**
    - Why: Multiple threads accessing same data
    - Benefit: No race conditions
    - Trade-off: Slight performance overhead

---

## END OF LLD INTERVIEW

✅ **You now have:**
- Clarifying questions
- Class structure (fields & methods with WHY)
- Complete implementation (all code - 4 algorithms)
- Working demo (5 scenarios)
- Requirements (functional & non-functional)
- Scalability strategy (5 layers)
- Design patterns explained (4 patterns)
- 10 Follow-up Q&A with detailed answers
- Algorithm comparison included
- Testing strategy included

**Everything ready for Rate Limiter interview!** 🚀