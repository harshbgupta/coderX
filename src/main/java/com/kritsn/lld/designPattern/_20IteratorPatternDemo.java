package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

// Step 1: Iterator interface
interface ProductIterator {
    boolean hasNext();
    ProductDummy next();
}

// Step 3: the collection — internal structure (a List) is hidden from client
class ProductCatalog implements Iterable<ProductDummy> {
    private final List<ProductDummy> products = new ArrayList<>();

    void addProduct(ProductDummy p) { products.add(p); }

    // Step 2: returns an Iterator, hiding the internal List entirely
    public ProductIterator createIterator() {
        return new CatalogIterator();
    }

    // Step 4: concrete Iterator — knows how to walk THIS structure
    private class CatalogIterator implements ProductIterator {
        private int position = 0; // cursor state lives HERE, not on the collection

        public boolean hasNext() {
            return position < products.size();
        }
        public ProductDummy next() {
            return products.get(position++);
        }
    }

    // bonus: implementing java.lang.Iterable lets us use Java's built-in for-each too
    public java.util.Iterator<ProductDummy> iterator() {
        return products.iterator();
    }
}

class ProductDummy {
    String name;
    ProductDummy(String name) { this.name = name; }
}

public class _20IteratorPatternDemo {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new ProductDummy("Shoe"));
        catalog.addProduct(new ProductDummy("Jacket"));
        catalog.addProduct(new ProductDummy("Cap"));

        // Step 5: client uses hasNext()/next() — never touches the internal List
        ProductIterator it = catalog.createIterator();
        while (it.hasNext()) {
            System.out.println("Custom iterator: " + it.next().name);
        }

        // Java's built-in Iterator pattern via Iterable — the for-each loop IS this pattern
        for (ProductDummy p : catalog) {
            System.out.println("For-each (built-in Iterator): " + p.name);
        }
    }
}