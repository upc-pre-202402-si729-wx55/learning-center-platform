package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get an enrollment by an Acme student record id and a course id.
 */
public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(AcmeStudentRecordId studentRecordId, Long courseId) {
    /**
     * Constructor
     * @param studentRecordId The Acme student record id.
     *                        Must not be null or blank.
     * @param courseId The course id.
     *                 Must not be null or less than or equal to zero.
     * @throws IllegalArgumentException If the student record id is null or blank or the course id is null or less than or equal to zero.
     */
    public GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("Student record id must not be null or blank.");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id must not be null or less than or equal to zero.");
        }
    }
}
