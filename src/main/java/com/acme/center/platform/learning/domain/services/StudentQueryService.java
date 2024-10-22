package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

/**
 * Service to query students
 */
public interface StudentQueryService {
    /**
     * Get a student by its profile id
     *
     * @param query the query containing the profile id
     * @return the student if found
     */
    Optional<Student> handle(GetStudentByProfileIdQuery query);

    /**
     * Get a student by its acme student record id
     *
     * @param query the query containing the acme student record id
     * @return the student if found
     */
    Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query);
}
