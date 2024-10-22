package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to confirm an enrollment.
 * @param enrollmentId the enrollment id
 */
public record ConfirmEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor.
     * @param enrollmentId the enrollment id
     *                     cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the enrollmentId is null or less than or equal to 0
     */
    public ConfirmEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
        }
    }
}
