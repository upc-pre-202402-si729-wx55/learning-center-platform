package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get all enrollments by Acme student record id.
 */
public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
    /**
     * Constructor.
     *
     * @param studentRecordId Acme student record id.
     *                        Cannot be null or blank.
     *                        Cannot be empty.
     * @throws IllegalArgumentException if any of the constraints are not met.
     * @see AcmeStudentRecordId
     */
    public GetAllEnrollmentsByAcmeStudentRecordIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("Student record id cannot be null or blank");
        }
    }
}
