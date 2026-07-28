package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */
// Step 1: the interface your code already expects
interface InventoryService {
    int getStock(String skuId);
}

// Step 2: the existing class you can't change — different method name/signature
class LegacyWarehouseApi {
    int fetchQuantityBySku(String sku) {
        System.out.println("Legacy system checking sku: " + sku);
        return 42;
    }
}

// Step 3: the Adapter — implements target, holds+delegates to adaptee
class InventoryAdapter implements InventoryService {
    private final LegacyWarehouseApi legacyApi;

    InventoryAdapter(LegacyWarehouseApi legacyApi) {
        this.legacyApi = legacyApi;
    }

    public int getStock(String skuId) {
        return legacyApi.fetchQuantityBySku(skuId); // translation happens here, and only here
    }
}

public class _06AdapterDemo {
    public static void main(String[] args) {
        LegacyWarehouseApi legacy = new LegacyWarehouseApi();

        // Step 4: caller only ever depends on InventoryService
        InventoryService inventory = new InventoryAdapter(legacy);
        int stock = inventory.getStock("SKU123");
        System.out.println("Stock available: " + stock);
    }
}
