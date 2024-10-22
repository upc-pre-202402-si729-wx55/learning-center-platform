package com.acme.center.platform.learning.application.internal.queryservices;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;
import com.acme.center.platform.learning.domain.services.CourseQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {
    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return List.of();
    }

    @Override
    public Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query) {
        return Optional.empty();
    }
}
