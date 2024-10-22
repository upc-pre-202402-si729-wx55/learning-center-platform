package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {
    @Override
    public Long handle(CreateCourseCommand command) {
        return 0L;
    }

    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteCourseCommand command) {

    }

    @Override
    public void handle(AddTutorialToCourseLearningPathCommand command) {

    }
}
