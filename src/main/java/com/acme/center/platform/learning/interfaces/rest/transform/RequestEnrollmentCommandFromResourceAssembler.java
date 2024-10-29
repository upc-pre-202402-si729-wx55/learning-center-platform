package com.acme.center.platform.learning.interfaces.rest.transform;


import com.acme.center.platform.learning.domain.model.commands.RequestEnrollmentCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.interfaces.rest.resources.RequestEnrollmentResource;

/**
 * Assembler to convert a RequestEnrollmentResource to a RequestEnrollmentCommand.
 */
public class RequestEnrollmentCommandFromResourceAssembler {
    /**
     * Converts a RequestEnrollmentResource to a RequestEnrollmentCommand.
     *
     * @param resource The {@link RequestEnrollmentResource} resource to convert.
     * @return The {@link RequestEnrollmentCommand} command
     */
    public static RequestEnrollmentCommand toCommandFromResource(RequestEnrollmentResource resource) {
        return new RequestEnrollmentCommand(new AcmeStudentRecordId(resource.studentRecordId()), resource.courseId());
    }
}
