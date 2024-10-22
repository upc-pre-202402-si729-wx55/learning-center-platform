package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;

/**
 * Query to get a student by a profile id.
 */
public record GetStudentByProfileIdQuery(ProfileId profileId) {
    /**
     * Constructor
     * @param profileId The profile id.
     *                  Must not be null or less than or equal to zero.
     * @throws IllegalArgumentException If the profile id is null or less than or equal to zero.
     */
    public GetStudentByProfileIdQuery {
        if (profileId == null || profileId.profileId() == null || profileId.profileId() <= 0) {
            throw new IllegalArgumentException("Profile id must not be null or less than or equal to zero.");
        }
    }
}
