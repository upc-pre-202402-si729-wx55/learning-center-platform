package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get a learning path item by a course id and a tutorial id.
 */
public record GetLearningPathItemByCourseIdAndTutorialIdQuery(Long courseId, Long tutorialId) {
    /**
     * Constructor
     * @param courseId The course id.
     *                 Must not be null or less than or equal to zero.
     * @param tutorialId The tutorial id.
     *                   Must not be null or less than or equal to zero.
     * @throws IllegalArgumentException If the course id is null or less than or equal to zero or the tutorial id is null or less than or equal to zero.
     */
    public GetLearningPathItemByCourseIdAndTutorialIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id must not be null or less than or equal to zero.");
        }
        if (tutorialId == null || tutorialId <= 0) {
            throw new IllegalArgumentException("Tutorial id must not be null or less than or equal to zero.");
        }
    }
}
