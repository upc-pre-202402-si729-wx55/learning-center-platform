package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for a learning path item.
 */
public record LearningPathItemResource(Long learningPathItemId, Long courseId, Long tutorialId) { }
