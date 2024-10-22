package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to add a tutorial to a course learning path.
 * @param tutorialId the tutorial id
 * @param courseId the course id
 */
public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {
    /**
     * Constructor.
     * @param tutorialId the tutorial id
     *                   cannot be null or less than or equal to 0
     * @param courseId the course id
     *                 cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the tutorialId or the courseId is null or less than or equal to 0
     */
    public AddTutorialToCourseLearningPathCommand {
        if (tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("tutorialId cannot be null or less than or equal to 0");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        }
    }
}
