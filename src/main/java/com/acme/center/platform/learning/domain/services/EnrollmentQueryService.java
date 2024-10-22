package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Service to query enrollments
 */
public interface EnrollmentQueryService {
    /**
     * Get all enrollments by acme student record id
     *
     * @param query the query containing the acme student record id
     * @return the list of enrollments
     */
    List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query);

    /**
     * Get an enrollment by its id
     *
     * @param query the query containing the enrollment id
     * @return the enrollment if found
     */
    Optional<Enrollment> handle(GetEnrollmentByIdQuery query);

    /**
     * Get all enrollments
     *
     * @param query the query to get all enrollments
     * @return the list of enrollments
     */
    List<Enrollment> handle(GetAllEnrollmentsQuery query);

    /**
     * Get all enrollments by course id
     *
     * @param query the query containing the course id
     * @return the list of enrollments
     */
    List<Enrollment> handle(GetAllEnrollmentsByCourseIdQuery query);

    /**
     * Get an enrollment by acme student record id and course id
     *
     * @param query the query containing the acme student record id and course id
     * @return the enrollment if found
     */
    Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query);
}
