package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for a student.
 */
public record StudentResource(String acmeStudentRecordId, Long profileId, Integer totalCompletedCourses, Integer totalCompletedTutorials) { }
