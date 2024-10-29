package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for updating a course.
 */
public record UpdateCourseResource(String title, String description) {
    public UpdateCourseResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
