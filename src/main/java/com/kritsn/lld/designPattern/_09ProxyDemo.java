package com.kritsn.lld.designPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

// Step 1: common interface
interface CatalogService {
    CatalogProduct getProduct(String skuId);
}

// Step 2: real object — expensive to call, no awareness of proxy
class RealCatalogService implements CatalogService {
    public CatalogProduct getProduct(String skuId) {
        System.out.println("Hitting DB/Elasticsearch for " + skuId + "...");
        return new CatalogProduct(skuId, "Sample Product"); // simulate expensive fetch
    }
}

// Step 3 & 4: Proxy — same interface, adds caching, then delegates
class CachingCatalogProxy implements CatalogService {
    private final RealCatalogService real = new RealCatalogService();
    private final Map<String, CatalogProduct> cache = new HashMap<>();

    public CatalogProduct getProduct(String skuId) {
        return cache.computeIfAbsent(skuId, id -> {
            System.out.println("Cache miss for " + id);
            return real.getProduct(id);
        });
    }
}

class CatalogProduct {
    String sku, name;
    CatalogProduct(String sku, String name) { this.sku = sku; this.name = name; }
}

public class _09ProxyDemo {
    public static void main(String[] args) {
        // Step 5: caller only ever depends on CatalogService
        CatalogService catalog = new CachingCatalogProxy();

        catalog.getProduct("SKU1"); // cache miss — hits real service
        catalog.getProduct("SKU1"); // cache hit — real service never called again
    }
}
