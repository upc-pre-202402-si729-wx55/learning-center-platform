package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

/**
 * This interface represents the service that handles the commands related to the Course aggregate.
 */
public interface CourseCommandService {
    /**
     * Handles the command to create a new course.
     *
     * @param command The command to create a new course containing the course data.
     * @return The created course ID.
     */
    Long handle(CreateCourseCommand command);

    /**
     * Handles the command to update an existing course.
     *
     * @param command The command to update an existing course containing the course data.
     * @return The updated course.
     */
    Optional<Course> handle(UpdateCourseCommand command);

    /**
     * Handles the command to delete an existing course.
     *
     * @param command The command to delete an existing course containing the course ID.
     */
    void handle(DeleteCourseCommand command);

    /**
     * Handles the command to add a tutorial to the learning path of a course.
     *
     * @param command The command to add a tutorial to the learning path of a course containing the course ID and tutorial ID.
     */
    void handle(AddTutorialToCourseLearningPathCommand command);
}
