package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.*;

/**
 * Service to handle enrollment commands
 */
public interface EnrollmentCommandService {
    /**
     * Handle a request enrollment command
     *
     * @param command the command to request enrollment containing the student id and course id
     * @return the enrollment id
     */
    Long handle(RequestEnrollmentCommand command);

    /**
     * Handle a confirm enrollment command
     *
     * @param command the command to confirm enrollment containing the enrollment id
     * @return the enrollment id
     */
    Long handle(ConfirmEnrollmentCommand command);

    /**
     * Handle a reject enrollment command
     *
     * @param command the command to reject enrollment containing the enrollment id
     * @return the enrollment id
     */
    Long handle(RejectEnrollmentCommand command);

    /**
     * Handle a cancel enrollment command
     *
     * @param command the command to cancel enrollment containing the enrollment id
     * @return the enrollment id
     */
    Long handle(CancelEnrollmentCommand command);

    /**
     * Handle a complete tutorial for enrollment command
     *
     * @param command the command to complete tutorial for enrollment containing the enrollment id and tutorial id
     * @return the enrollment id
     */
    Long handle(CompleteTutorialForEnrollmentCommand command);
}
