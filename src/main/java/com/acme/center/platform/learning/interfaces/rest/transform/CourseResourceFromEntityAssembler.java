package com.acme.center.platform.learning.interfaces.rest.transform;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.interfaces.rest.resources.CourseResource;

/**
 * Assembler to convert a Course entity to a CourseResource.
 */
public class CourseResourceFromEntityAssembler {
    /**
     * Converts a Course entity to a CourseResource.
     *
     * @param entity The {@link Course} entity to convert.
     * @return The {@link CourseResource} resource
     */
    public static CourseResource toResourceFromEntity(Course entity) {
        return new CourseResource(entity.getId(), entity.getTitle(), entity.getDescription());
    }
}
