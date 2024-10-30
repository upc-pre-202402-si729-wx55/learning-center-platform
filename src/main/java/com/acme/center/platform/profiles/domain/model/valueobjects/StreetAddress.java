package com.acme.center.platform.profiles.domain.model.valueobjects;

/**
 * StreetAddress Value Object
 */
public record StreetAddress(
        String street,
        String number,
        String city,
        String postalCode,
        String country) {

    /**
     * Default constructor
     */
    public StreetAddress() {
        this(null, null, null, null, null);
    }

    /**
     * StreetAddress constructor with street, number, city, postal code and country
     * @param street Street
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     */
    public StreetAddress(String street, String city, String postalCode, String country) {
        this(street, null, city, postalCode, country);
    }

    /**
     * Get street address as a single value
     * @return Street address as a string
     */
    public String getStreetAddress() {
        return "%s %s, %s, %s, %s".formatted(street, number, city, postalCode, country);
    }

    /**
     * StreetAddress constructor with validation
     * @param street Street
     * @param number Number
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     */
    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street must not be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must not be null or blank");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code must not be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country must not be null or blank");
        }
    }
}
