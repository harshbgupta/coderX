package com.kritsn.ivs.epam;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2025
 */

//make this class as Immutable
final public class ImmutableClass {
    private final String name; //must be final to make it immutable
    private final int age; //must be final to make it immutable

    public ImmutableClass(String name, int age) {
        this.name = name;
        this.age = age;
        //both var initialized
    }

    public String getName() {
        return name; // safe because String is immutable
    }

    public int getAge() {
        return age; // primitive -> safe
    }
}

/**
 * Why this class is immutable?
 * 	1.	final class
 * 	•	Prevents inheritance.
 * 	•	Nobody can extend and add mutating behavior.
 * 	2.	private final fields
 * 	•	private → no external direct access.
 * 	•	final → fields can only be assigned once, inside the constructor.
 * 	3.	All fields initialized in constructor
 * 	•	Ensures complete initialization at object creation.
 * 	•	No “half-constructed” objects.
 * 	4.	No setters
 * 	•	No method exists that can mutate state.
 * 	5.	Safe return types in getters
 * 	•	String → immutable by nature.
 * 	•	int → primitive, cannot be changed externally.
 */
