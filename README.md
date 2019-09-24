# Single Item Lists

This project explores the differences in approaches for creating a single-item or single-element list. The approaches are enumerated by the `SingleItemListCreationApproach` class and
are assessed in different ways using the various `*Harness` classes.

## Building

    mvn clean install
    
## Running

It's best to run specific harness by executing their `main` methods using an IDE. If you run the uber jar directly, it will execute the performance assessment harness.

    java -jar target\single-element-list-1.0-SNAPSHOT-jar-with-dependencies.jar