package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Command to request enrollment.
 * @param studentRecordId the student record id
 * @param courseId the course id
 */
public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId, Long courseId) {
    /**
     * Constructor.
     * @param studentRecordId the student record id
     *                        cannot be null or blank
     * @param courseId the course id
     *                 cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the studentRecordId is null or blank
     *                                  or the courseId is null or less than or equal to 0
     */
    public RequestEnrollmentCommand {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("studentRecordId cannot be null or blank");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        }
    }
}
