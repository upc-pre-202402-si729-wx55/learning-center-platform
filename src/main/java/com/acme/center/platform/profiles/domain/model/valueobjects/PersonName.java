package com.acme.center.platform.profiles.domain.model.valueobjects;

/**
 * PersonName Value Object
 */
public record PersonName(String firstName, String lastName) {
    /**
     * Default constructor
     */
    public PersonName() {
        this(null, null);
    }

    /**
     * Full name getter
     * @return Full name
     */
    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    /**
     * Constructor with validation
     * @param firstName First name
     * @param lastName Last name
     */
    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name must not be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name must not be null or blank");
        }
    }

}
