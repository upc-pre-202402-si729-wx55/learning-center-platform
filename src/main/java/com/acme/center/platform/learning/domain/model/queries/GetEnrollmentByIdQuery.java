package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get an enrollment by its id.
 */
public record GetEnrollmentByIdQuery(Long enrollmentId) {
    /**
     * Constructor
     * @param enrollmentId The enrollment id.
     *                     Must not be null or less than or equal to zero.
     * @throws IllegalArgumentException If the enrollment id is null or less than or equal to zero.
     */
    public GetEnrollmentByIdQuery {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("Enrollment id must not be null or less than or equal to zero.");
        }
    }
}
