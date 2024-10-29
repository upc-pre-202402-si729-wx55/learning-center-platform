package com.acme.center.platform.learning.interfaces.rest.transform;

import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.interfaces.rest.resources.CreateCourseResource;

/**
 * Assembler to convert a CreateCourseResource to a CreateCourseCommand.
 */
public class CreateCourseCommandFromResourceAssembler {
    /**
     * Converts a CreateCourseResource to a CreateCourseCommand.
     *
     * @param resource The {@link CreateCourseResource} resource to convert.
     * @return The {@link CreateCourseCommand} command
     */
    public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {
        return new CreateCourseCommand(resource.title(), resource.description());
    }
}
