package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class implements the CourseCommandService interface.
 */
@Service
public class CourseCommandServiceImpl implements CourseCommandService {
    private final CourseRepository courseRepository;

    /**
     * Constructor of the class.
     *
     * @param courseRepository The repository of the course.
     */
    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // inherited javadoc
    @Override
    public Long handle(CreateCourseCommand command) {
        if (courseRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Course with title %s already exists".formatted(command.title()));
        var course = new Course(command);
        try {
            courseRepository.save(course);
            return course.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating course: %s".formatted(e.getMessage()));
        }
    }

    // inherited javadoc
    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        if (courseRepository.existsByTitleAndIdIsNot(command.title(), command.courseId()))
            throw new IllegalArgumentException("Course with title %s already exists".formatted(command.title()));
        var result = courseRepository.findById(command.courseId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        var courseToUpdate = result.get();
        try {
            var updatedCourse = courseRepository.save(courseToUpdate.updateInformation(command.title(), command.description()));
            return Optional.of(updatedCourse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating course: %s".formatted(e.getMessage()));
        }
    }

    // inherited javadoc
    @Override
    public void handle(DeleteCourseCommand command) {
        if(!courseRepository.existsById(command.courseId()))
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        try {
            courseRepository.deleteById(command.courseId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting course: %s".formatted(e.getMessage()));
        }
    }

    // inherited javadoc
    @Override
    public void handle(AddTutorialToCourseLearningPathCommand command) {
        if(!courseRepository.existsById(command.courseId()))
            throw new IllegalArgumentException("Course with id %s not found".formatted(command.courseId()));
        try {
            courseRepository.findById(command.courseId()).map(course -> {
                course.addTutorialToLearningPath(command.tutorialId());
                courseRepository.save(course);
                return course;
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error adding tutorial to course learning path: %s".formatted(e.getMessage()));
        }
    }
}
