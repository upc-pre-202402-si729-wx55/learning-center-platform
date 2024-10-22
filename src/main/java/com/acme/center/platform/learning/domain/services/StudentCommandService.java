package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Service to handle student commands
 */
public interface StudentCommandService {

    /**
     * Handle a create student command
     *
     * @param command the command to create a student
     * @return the ACME Student Record ID of the created student
     */
    AcmeStudentRecordId handle(CreateStudentCommand command);

    /**
     * Handle an update student metrics on tutorial completed command
     *
     * @param command the command to update student metrics on tutorial completed
     * @return the ACME Student Record ID of the updated student
     */
    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
