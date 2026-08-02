package com.kritsn.lld.urlshortner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
    SCALE & VOLUME:
    - How many URLs to shorten? (millions? billions?)
    - Requests per second? (1K? 10K?)
    - Read/Write ratio?

    FUNCTIONAL:
    - Custom short URLs or auto-generated?
    - Expiry time for URLs?
    - Analytics/click tracking needed?
    - Delete operation required?

    CONSTRAINTS:
    - Predictable or random short codes?
    - User accounts needed?
    - Bulk operations?

    Entities:
    Interfaces (3)
    - URLRepository - Data Access Contract
    - URLCache - Caching Contract
    - URLService - Business Logic Contract

    Classes (5)
    - URLMapper - Entity (holds URL data)
    - CodeGenerator - Generates unique codes (Singleton)
    - URLRepositoryImpl - Implements URLRepository
    - URLCacheImpl - Implements URLCache
    - URLServiceImpl - Implements URLService

    Exceptions (3)
    - InvalidURLException
    - DuplicateShortCodeException
    - URLNotFoundException
 */
class URLMapper {
    private String shortCode;
    private String originalURL;
    private long createdAt;

    public URLMapper(String shortCode, String originalURL) {
        this.shortCode = shortCode;
        this.originalURL = originalURL;
        this.createdAt = System.currentTimeMillis();
    }

    public boolean isValid() {
        return originalURL != null &&
                originalURL.startsWith("http") &&
                shortCode.length() >= 4 &&
                shortCode.length() <= 10;
    }

    public String getShortCode() { return shortCode; }
    public String getOriginalURL() { return originalURL; }
    public long getCreatedAt() { return createdAt; }
}

final class CodeGenerator {
    private static CodeGenerator instance;
    private long sequenceCounter = 0;
    private final String ALPHABET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private CodeGenerator() {}

    public static synchronized CodeGenerator getInstance() {
        if (instance == null) {
            instance = new CodeGenerator();
        }
        return instance;
    }

    /**
     * Generate unique code using Base62 encoding
     *
     * Algorithm:
     *   1. Get next sequence (atomic counter)
     *   2. Convert to base62 string
     *   3. Return code
     *
     * Examples:
     *   Counter 0 → 'a'
     *   Counter 1 → 'b'
     *   Counter 62 → '10' (in base62)
     */
    public synchronized String generate() {
        long number = sequenceCounter++;
        return encodeToBase62(number);
    }

    private String encodeToBase62(long num) {
        if (num == 0) return "a";
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            result.append(ALPHABET.charAt((int)(num % 62)));
            num /= 62;
        }
        return result.reverse().toString();
    }
}

interface URLRepository{
    void save(URLMapper mapper) throws DuplicateShortCodeException;
    URLMapper findByShortCode(String shortCode);
    boolean exists(String shortCode);
}

class URLRepositoryImpl implements URLRepository {
    private Map<String, URLMapper> database;

    public URLRepositoryImpl() {
        this.database = new ConcurrentHashMap<>();
    }

    /**
     * Save URL mapping to database
     * Real DB: INSERT INTO urls (short_code, original_url) VALUES (?, ?)
     * Thread-safe: ConcurrentHashMap
     */
    @Override
    public synchronized void save(URLMapper mapper)
            throws DuplicateShortCodeException {

        if (database.containsKey(mapper.getShortCode())) {
            throw new DuplicateShortCodeException(
                    "Code exists: " + mapper.getShortCode());
        }
        database.put(mapper.getShortCode(), mapper);
        System.out.println("[DB] Saved: " + mapper.getShortCode());
    }

    @Override
    public URLMapper findByShortCode(String shortCode) {
        return database.get(shortCode);
    }

    @Override
    public boolean exists(String shortCode) {
        return database.containsKey(shortCode);
    }

    public void clear() {
        database.clear();
    }
}

interface URLCache {
    URLMapper get(String shortCode);
    void put(String shortCode, URLMapper mapper);
    void clear();

}
class URLCacheImpl implements URLCache {
    private Map<String, URLMapper> cache;
    private Map<String, Long> expiry;
    private final long CACHE_TTL = 3600000; // 1 hour

    public URLCacheImpl() {
        this.cache = new ConcurrentHashMap<>();
        this.expiry = new ConcurrentHashMap<>();
    }

    /**
     * Get from cache with expiry validation
     *
     * Logic:
     *   1. Check if key exists
     *   2. Check if expired (TTL)
     *   3. If valid: return, else remove and return null
     */
    @Override
    public URLMapper get(String shortCode) {
        if (!cache.containsKey(shortCode)) {
            return null;
        }

        if (System.currentTimeMillis() > expiry.get(shortCode)) {
            cache.remove(shortCode);
            expiry.remove(shortCode);
            return null;
        }

        System.out.println("[CACHE] Hit: " + shortCode);
        return cache.get(shortCode);
    }

    @Override
    public void put(String shortCode, URLMapper mapper) {
        cache.put(shortCode, mapper);
        expiry.put(shortCode, System.currentTimeMillis() + CACHE_TTL);
        System.out.println("[CACHE] Stored: " + shortCode);
    }

    @Override
    public void clear() {
        cache.clear();
        expiry.clear();
    }

    public int size() {
        return cache.size();
    }
}

interface URLService{
    String createShortURL(String longURL) throws Exception;
    String getOriginalURL(String shortCode) throws URLNotFoundException;
    void printStats();
}

class URLServiceImpl implements URLService {
    private URLRepository repository;
    private URLCache cache;
    private CodeGenerator codeGen;

    public URLServiceImpl(URLRepository repo, URLCache cache) {
        this.repository = repo;
        this.cache = cache;
        this.codeGen = CodeGenerator.getInstance();
    }

    /**
     * CREATE SHORT URL
     *
     * Flow:
     *   1. Validate input URL
     *   2. Generate unique code (atomic counter + base62)
     *   3. Create URLMapper entity
     *   4. Save to database
     *   5. Cache the mapping
     *   6. Return short URL
     *   7. Handle collisions via retry
     */
    @Override
    public String createShortURL(String longURL) throws Exception {
        // Step 1: Validate
        if (longURL == null || !longURL.startsWith("http")) {
            throw new InvalidURLException("Invalid URL: " + longURL);
        }
        System.out.println("[CREATE] Processing: " + longURL);

        // Step 2: Generate code (atomic)
        String shortCode = codeGen.generate();
        System.out.println("[CREATE] Generated: " + shortCode);

        // Step 3: Create entity
        URLMapper mapper = new URLMapper(shortCode, longURL);
        if (!mapper.isValid()) {
            throw new InvalidURLException("Validation failed");
        }

        try {
            // Step 4: Save to DB
            repository.save(mapper);

            // Step 5: Cache it
            cache.put(shortCode, mapper);

            // Step 6: Return
            return "https://short.url/" + shortCode;

        } catch (DuplicateShortCodeException e) {
            // Handle collision: retry with new code
            System.out.println("[CREATE] Collision, retrying...");
            return createShortURL(longURL);
        }
    }

    /**
     * RETRIEVE ORIGINAL URL
     *
     * Flow:
     *   1. Try cache first (fast path - O(1))
     *   2. If miss: Query database (O(log n))
     *   3. Update cache on hit
     *   4. Return original URL or throw exception
     *
     * Pattern: Cache-Aside (load on miss)
     */
    @Override
    public String getOriginalURL(String shortCode) throws URLNotFoundException {
        System.out.println("[RETRIEVE] Looking up: " + shortCode);

        // Step 1: Try cache (fast)
        URLMapper cached = cache.get(shortCode);
        if (cached != null) {
            System.out.println("[RETRIEVE] Found in cache");
            return cached.getOriginalURL();
        }

        // Step 2: Try DB (slower)
        URLMapper dbResult = repository.findByShortCode(shortCode);
        if (dbResult == null) {
            throw new URLNotFoundException("URL not found: " + shortCode);
        }
        System.out.println("[RETRIEVE] Found in DB");

        // Step 3: Update cache for next time
        cache.put(shortCode, dbResult);

        return dbResult.getOriginalURL();
    }

    @Override
    public void printStats() {
        System.out.println("\n[STATS] Cache size: " +
                ((URLCacheImpl)cache).size());
    }
}

class InvalidURLException extends Exception {
    public InvalidURLException(String msg) { super(msg); }
}

class DuplicateShortCodeException extends Exception {
    public DuplicateShortCodeException(String msg) { super(msg); }
}

class URLNotFoundException extends Exception {
    public URLNotFoundException(String msg) { super(msg); }
}

public class UrlShortenerDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("========== URL SHORTENER DEMO ==========\n");

        // Create components
        URLRepository repository = new URLRepositoryImpl();
        URLCache cache = new URLCacheImpl();
        URLService service = new URLServiceImpl(repository, cache);
        System.out.println("\n-- Create short URLs --");
        String longUrl1 = "http://example.com/articles/long-article-1";
        String longUrl2 = "https://another.site/blog/post-42";

        String short1 = service.createShortURL(longUrl1);
        System.out.println("Shortened: " + longUrl1 + " -> " + short1);

        String short2 = service.createShortURL(longUrl2);
        System.out.println("Shortened: " + longUrl2 + " -> " + short2);

        // Extract short codes (after last '/')
        String code1 = short1.substring(short1.lastIndexOf('/') + 1);
        String code2 = short2.substring(short2.lastIndexOf('/') + 1);

        System.out.println("\n-- Retrieve original URLs (first retrieval will be DB -> cache) --");
        String retrieved1 = service.getOriginalURL(code1);
        System.out.println("Retrieved for " + code1 + " -> " + retrieved1);

        System.out.println("\n-- Retrieve again (should hit cache) --");
        String retrieved1b = service.getOriginalURL(code1);
        System.out.println("Retrieved for " + code1 + " -> " + retrieved1b);

        System.out.println("\n-- Stats --");
        service.printStats();

        System.out.println("\n-- Clear cache and retrieve (forces DB lookup) --");
        ((URLCacheImpl) cache).clear();
        String retrievedAfterClear = service.getOriginalURL(code1);
        System.out.println("Retrieved after cache clear: " + retrievedAfterClear);

        System.out.println("\n-- Demonstrate DuplicateShortCodeException by directly saving existing code --");
        try {
            // Attempt to save a mapper with the same short code -> should throw
            URLMapper colliding = new URLMapper(code1, "http://malicious.example/x");
            repository.save(colliding);
        } catch (DuplicateShortCodeException e) {
            System.out.println("Caught expected duplicate exception: " + e.getMessage());
        }

        System.out.println("\nDemo complete.");
    }
}
