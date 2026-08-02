package com.kritsn.lld.ratelimmiter;

/*
    Questions to Ask Interviewer

    SCALE & CONFIGURATION:
    - Per user or per API endpoint?
    - How many requests allowed per window? (1K? 10K?)
    - Time window? (per second? per minute? per hour?)
    - Single server or distributed (multiple servers)?

    ALGORITHM:
    - Which algorithm? (Token Bucket, Sliding Window, Leaky Bucket, Fixed Window)
    - Burst allowed or strict limit?
    - Soft limit (warn) or hard limit (reject)?

    BEHAVIOR:
    - What happens when limit exceeded? (queue, reject, or delay)
    - Retry-After header needed?
    - Track across distributed systems?

    EDGE CASES:
    - What if time sync issues across servers?
    - Expired tokens handling?
    - Multiple regions support?

    ---------------------
    Entity:
    Interface
    - RequestProcessor

    class
    - RteLimiter
    - RateLimitingConfig
    - User
    - Request
    - TimeWindow
    - TokenBucket: Time window management
    - RateLimitingStrtegey: interface -> Stretegy Pattern
    - SlidingWindow: Token bucket algorithm (Strategy)
    - LeakyBucket: Leaky bucket algorithm (Strategy)
    - FixedWindow: Fixed window algorithm (Strategy)
    - RateLimitResponse

    Error:
    - RateLimitExceededException
    - InvalidConfigException
    - RequestRejectedException
 */

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

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

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSize() {
        return windowSize;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}

class User {
    private String userId;
    private long createdAt;

    public User(String userId) {
        this.userId = userId;
        this.createdAt = System.currentTimeMillis();
    }

    public String getUserId() {
        return userId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}

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

    public String getRequestId() {
        return requestId;
    }

    public String getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

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

    public boolean isAllowed() {
        return isAllowed;
    }

    public int getTokensRemaining() {
        return tokensRemaining;
    }

    public long getRetryAfterMs() {
        return retryAfterMs;
    }

    public String getMessage() {
        return message;
    }
}

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

    public long getWindowSize() {
        return windowSize;
    }
}

interface RateLimitingStrategy {
    /**
     * Strategy Pattern - Why: Different algorithms
     * Benefit: Easy to swap FCFS, SCAN, LOOK without changing RateLimiter
     * Trade-off: More classes, but cleaner design
     */
    RateLimitResponse allowRequest(String userId, long timestamp);

    void reset(String userId);
}

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
     * <p>
     * Logic:
     * 1. Calculate elapsed time since last refill
     * 2. Add tokens: elapsed * refillRate
     * 3. Cap at maxTokens (can't exceed max)
     * 4. If tokens >= 1: consume 1 token, allow request
     * 5. Else: deny request, tell when retry
     * <p>
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

            return new RateLimitResponse(true, (int) tokens, 0);
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
     * <p>
     * Logic:
     * 1. Get queue of request timestamps for user
     * 2. Remove expired requests (older than windowSize)
     * 3. If queue.size() < maxRequests: add request, allow
     * 4. Else: deny request
     * <p>
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
     * <p>
     * Logic:
     * 1. Check if current window expired
     * 2. If expired: reset count and start new window
     * 3. If count < maxRequests: increment count, allow
     * 4. Else: deny (window limit reached)
     * <p>
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
     * <p>
     * Flow:
     * 1. Register user if new
     * 2. Delegate to strategy
     * 3. Return response
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

class RateLimitExceededException extends Exception {
    public RateLimitExceededException(String msg) {
        super(msg);
    }
}

class InvalidConfigException extends Exception {
    public InvalidConfigException(String msg) {
        super(msg);
    }
}

class RequestRejectedException extends Exception {
    public RequestRejectedException(String msg) {
        super(msg);
    }
}

public class RateLimiterDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("========== RATE LIMITER DEMO ==========\n");

        // ------------------------------------------------------------------
        // TOKEN_BUCKET demo: 5 requests per 1000ms (allows bursts up to 5)
        // ------------------------------------------------------------------
        System.out.println("---- TOKEN_BUCKET Demo (5 req / 1000ms) ----");
        RateLimitConfig configToken = new RateLimitConfig(5, 1000, "TOKEN_BUCKET");
        RateLimiter tokenLimiter = new RateLimiter(configToken);

        String userA = "user-1";
        // Make 7 requests quickly; first 5 should be allowed, next 2 denied
        for (int i = 1; i <= 7; i++) {
            RateLimitResponse res = tokenLimiter.allowRequest(userA, "/api/resource");
            System.out.println("Attempt " + i + ": allowed=" + res.isAllowed()
                    + ", tokensRemaining=" + res.getTokensRemaining()
                    + ", retryAfterMs=" + res.getRetryAfterMs());
            Thread.sleep(150); // 150ms between attempts
        }

        // Wait long enough to allow tokens to refill and try again
        System.out.println("Sleeping 1200ms to allow tokens to refill...");
        Thread.sleep(1200);
        RateLimitResponse afterSleep = tokenLimiter.allowRequest(userA, "/api/resource");
        System.out.println("After sleep: allowed=" + afterSleep.isAllowed()
                + ", tokensRemaining=" + afterSleep.getTokensRemaining()
                + ", retryAfterMs=" + afterSleep.getRetryAfterMs());

        // ------------------------------------------------------------------
        // SLIDING_WINDOW demo: 3 requests per 2000ms (precise sliding window)
        // ------------------------------------------------------------------
        System.out.println("\n---- SLIDING_WINDOW Demo (3 req / 2000ms) ----");
        RateLimitConfig configSliding = new RateLimitConfig(3, 2000, "SLIDING_WINDOW");
        RateLimiter slidingLimiter = new RateLimiter(configSliding);

        String userB = "user-2";
        for (int i = 1; i <= 4; i++) {
            RateLimitResponse res = slidingLimiter.allowRequest(userB, "/api/data");
            System.out.println("Attempt " + i + ": allowed=" + res.isAllowed()
                    + ", tokensRemaining=" + res.getTokensRemaining()
                    + ", retryAfterMs=" + res.getRetryAfterMs());
            Thread.sleep(500);
        }

        System.out.println("Sleeping 2500ms to slide the window...");
        Thread.sleep(2500);
        RateLimitResponse afterSlide = slidingLimiter.allowRequest(userB, "/api/data");
        System.out.println("After sleep: allowed=" + afterSlide.isAllowed()
                + ", tokensRemaining=" + afterSlide.getTokensRemaining()
                + ", retryAfterMs=" + afterSlide.getRetryAfterMs());

        // ------------------------------------------------------------------
        // FIXED_WINDOW demo: 2 requests per 1000ms (simple fixed window)
        // ------------------------------------------------------------------
        System.out.println("\n---- FIXED_WINDOW Demo (2 req / 1000ms) ----");
        RateLimitConfig configFixed = new RateLimitConfig(2, 1000, "FIXED_WINDOW");
        RateLimiter fixedLimiter = new RateLimiter(configFixed);

        String userC = "user-3";
        for (int i = 1; i <= 3; i++) {
            RateLimitResponse res = fixedLimiter.allowRequest(userC, "/health");
            System.out.println("Attempt " + i + ": allowed=" + res.isAllowed()
                    + ", tokensRemaining=" + res.getTokensRemaining()
                    + ", retryAfterMs=" + res.getRetryAfterMs());
            Thread.sleep(300);
        }

        // Reset user and try again
        System.out.println("Resetting user-3 and trying again...");
        fixedLimiter.resetUser(userC);
        RateLimitResponse afterReset = fixedLimiter.allowRequest(userC, "/health");
        System.out.println("After reset: allowed=" + afterReset.isAllowed()
                + ", tokensRemaining=" + afterReset.getTokensRemaining()
                + ", retryAfterMs=" + afterReset.getRetryAfterMs());

        System.out.println("\nDemo complete.");
    }
}
