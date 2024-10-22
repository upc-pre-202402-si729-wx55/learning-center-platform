package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to create a student.
 * @param firstName the first name
 * @param lastName the last name
 * @param email the email
 * @param street the street
 * @param number the number
 * @param city the city
 * @param postalCode the postal code
 * @param country the country
 */
public record CreateStudentCommand(String firstName,
                                   String lastName,
                                   String email,
                                   String street,
                                   String number,
                                   String city,
                                   String postalCode,
                                   String country) {
    /**
     * Constructor.
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param street the street
     * @param number the number
     * @param city the city
     * @param postalCode the postal code
     * @param country the country
     * @throws IllegalArgumentException if the firstName, lastName, email, street, number, city, postalCode or country is null or blank
     */
    public CreateStudentCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or blank");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("street cannot be null or blank");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("number cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city cannot be null or blank");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("postalCode cannot be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("country cannot be null or blank");
        }
    }
}
