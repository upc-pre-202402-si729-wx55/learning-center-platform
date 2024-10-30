package com.acme.center.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.profiles.domain.model.aggregates.Profile;
import com.acme.center.platform.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Profile Repository
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    /**
     * Find a Profile by Email Address
     *
     * @param emailAddress The Email Address
     * @return A {@link Profile} instance if the email address is valid, otherwise empty
     */
    Optional<Profile> findByEmailAddress(EmailAddress emailAddress);

    /**
     * Check if a Profile exists by Email Address
     *
     * @param emailAddress The Email Address
     * @return True if the email address exists, otherwise false
     */
    boolean existsByEmailAddress(EmailAddress emailAddress);
}
