package com.acme.center.platform.learning.interfaces.rest.transform;

import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.center.platform.learning.interfaces.rest.resources.UpdateCourseResource;

/**
 * Assembler to convert a UpdateCourseResource to a UpdateCourseCommand.
 */
public class UpdateCourseCommandFromResourceAssembler {
    /**
     * Converts a UpdateCourseResource to a UpdateCourseCommand.
     *
     * @param courseId The course id.
     * @param resource The {@link UpdateCourseResource} resource to convert.
     * @return The {@link UpdateCourseCommand} command
     */
    public static UpdateCourseCommand toCommandFromResource(Long courseId, UpdateCourseResource resource) {
        return new UpdateCourseCommand(courseId, resource.title(), resource.description());
    }
}
