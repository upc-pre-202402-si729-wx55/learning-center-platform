package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service to query courses
 */
public interface CourseQueryService {
    /**
     * Get a course by its id
     *
     * @param query the query containing the course id
     * @return the course if found
     */
    Optional<Course> handle(GetCourseByIdQuery query);

    /**
     * Get all courses
     *
     * @param query the query to get all courses
     * @return the list of courses
     */
    List<Course> handle(GetAllCoursesQuery query);

    /**
     * Get a learning path item by course id and tutorial id
     *
     * @param query the query containing the course id and tutorial id
     * @return the learning path item if found
     */
    Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query);

}
