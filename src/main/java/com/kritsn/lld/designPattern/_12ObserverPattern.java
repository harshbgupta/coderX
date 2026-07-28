package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

// Step 1: Observer interface
interface StockObserver {
    void onStockUpdated(String skuId, int newQty);
}

// Step 2 & 3: Subject — holds observers, notifies them on state change
class InventorySubject {
    private final List<StockObserver> observers = new ArrayList<>();

    void subscribe(StockObserver observer) { observers.add(observer); }
    void unsubscribe(StockObserver observer) { observers.remove(observer); }

    void updateStock(String skuId, int newQty) {
        System.out.println("Stock updated for " + skuId + " -> " + newQty);
        for (StockObserver observer : observers) {
            observer.onStockUpdated(skuId, newQty); // notify every subscriber
        }
    }
}

// Step 4: concrete Observers — independent reactions
class ReindexObserver implements StockObserver {
    public void onStockUpdated(String skuId, int newQty) {
        System.out.println("  -> Reindexing " + skuId + " in Elasticsearch");
    }
}
class LowStockAlertObserver implements StockObserver {
    public void onStockUpdated(String skuId, int newQty) {
        if (newQty < 10) {
            System.out.println("  -> ALERT: " + skuId + " low stock (" + newQty + " left)");
        }
    }
}

public class _12ObserverPattern {
    public static void main(String[] args) {
        InventorySubject inventory = new InventorySubject();

        // Step 5: observers self-register — subject doesn't know concrete types
        inventory.subscribe(new ReindexObserver());
        inventory.subscribe(new LowStockAlertObserver());

        inventory.updateStock("SKU123", 5);
    }
}