package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to complete a tutorial for an enrollment.
 * @param enrollmentId the enrollment id
 * @param tutorialId the tutorial id
 */
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {
    /**
     * Constructor.
     * @param enrollmentId the enrollment id
     *                     cannot be null or less than or equal to 0
     * @param tutorialId the tutorial id
     *                   cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the enrollmentId or the tutorialId is null or less than or equal to 0
     */
    public CompleteTutorialForEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
        }
        if (tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("tutorialId cannot be null or less than or equal to 0");
        }
    }
}
