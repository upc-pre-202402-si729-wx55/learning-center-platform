package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for an enrollment.
 */
public record EnrollmentResource(Long enrollmentId, String studentRecordId, Long courseId, String status) { }
