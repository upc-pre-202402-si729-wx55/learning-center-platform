package com.acme.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

/**
 * EmailAddress Value Object
 */
@Embeddable
public record EmailAddress(@Email String address) {
    /**
     * Default constructor
     */
    public EmailAddress() {
        this(null);
    }
}
