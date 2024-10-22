package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Command to update student metrics on tutorial completed.
 * @param studentRecordId the student record id
 */
public record UpdateStudentMetricsOnTutorialCompletedCommand(AcmeStudentRecordId studentRecordId) {
    /**
     * Constructor.
     * @param studentRecordId the student record id
     *                        cannot be null or blank
     * @throws IllegalArgumentException if the studentRecordId is null or blank
     */
    public UpdateStudentMetricsOnTutorialCompletedCommand {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("studentRecordId cannot be null or blank");
        }
    }
}
