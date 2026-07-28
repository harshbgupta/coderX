package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */
// Step 2: abstract base class holding the fixed skeleton
abstract class CatalogIngestionJob {

    // Step 2 & 3: the template method — fixed sequence, marked final so
    // subclasses cannot reorder or skip steps
    final void run() {
        validate();
        transform();
        index();
    }

    // Step 3: default implementation — shared across all variants unless overridden
    void validate() {
        System.out.println("Validating source data...");
    }

    // Step 3: abstract — MUST vary per subclass, no sensible shared default
    abstract void transform();

    // Step 3: default implementation — shared across all variants
    void index() {
        System.out.println("Indexing transformed data to Elasticsearch...");
    }
}

// Step 4: concrete subclasses — override ONLY the varying step
class CsvIngestionJob extends CatalogIngestionJob {
    void transform() {
        System.out.println("Transforming CSV rows into catalog documents");
    }
}
class XmlIngestionJob extends CatalogIngestionJob {
    void transform() {
        System.out.println("Transforming XML nodes into catalog documents");
    }

    @Override
    void validate() { // overriding a DEFAULT step is allowed too, just not required
        System.out.println("Validating XML schema before processing...");
    }
}

public class _17TemplateMethodDemo {
    public static void main(String[] args) {
        CatalogIngestionJob csvJob = new CsvIngestionJob();
        csvJob.run();

        System.out.println("---");

        CatalogIngestionJob xmlJob = new XmlIngestionJob();
        xmlJob.run();
    }
}