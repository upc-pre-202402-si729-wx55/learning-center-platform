package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to cancel an enrollment.
 * @param enrollmentId the enrollment id
 */
public record CancelEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor.
     * @param enrollmentId the enrollment id
     *                     cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the enrollmentId is null or less than or equal to 0
     */
    public CancelEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
        }
    }
}
