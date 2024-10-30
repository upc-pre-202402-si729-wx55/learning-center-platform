package com.acme.center.platform.profiles.domain.model.aggregates;

import com.acme.center.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.center.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.center.platform.profiles.domain.model.valueobjects.PersonName;
import com.acme.center.platform.profiles.domain.model.valueobjects.StreetAddress;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

/**
 * Profile Aggregate Root
 */
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))})
    private EmailAddress emailAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street_address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "street_address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "street_address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "street_address_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "street_address_country"))})
    private StreetAddress streetAddress;

    /**
     * Constructor with first name, last name, email, street, number, city, postal code and country
     * @param firstName First name
     * @param lastName Last name
     * @param email Email
     * @param street Street
     * @param number Number
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     */
    public Profile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
        this.name = new PersonName(firstName, lastName);
        this.emailAddress = new EmailAddress(email);
        this.streetAddress = new StreetAddress(street, number, city, postalCode, country);
    }

    /**
     * Default constructor
     */
    public Profile() {}

    /**
     * Constructor with a CreateProfileCommand
     * @param command The {@link CreateProfileCommand} instance
     */
    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.emailAddress = new EmailAddress(command.email());
        this.streetAddress = new StreetAddress(command.street(), command.number(), command.city(), command.postalCode(), command.country());
    }

    /**
     * Full name getter
     * @return Full name
     */
    public String getFullName() {
        return name.getFullName();
    }

    /**
     * Email address getter
     * @return Email address
     */
    public String getEmailAddress() {
        return emailAddress.address();
    }

    /**
     * Street address getter
     * @return Street address
     */
    public String getStreetAddress() {
        return streetAddress.getStreetAddress();
    }

    /**
     * Update name
     * @param firstName First name
     * @param lastName Last name
     */
    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    /**
     * Update email address
     * @param email Email address
     */
    public void updateEmailAddress(String email) {
        this.emailAddress = new EmailAddress(email);
    }

}
