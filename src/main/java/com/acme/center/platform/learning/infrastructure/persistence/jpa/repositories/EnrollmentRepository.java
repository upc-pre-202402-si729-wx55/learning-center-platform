package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EnrollmentRepository
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Find all enrollments by student record id
     * @param studentRecordId the ACME Student Record ID of the student
     * @return a list of enrollments
     */
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

    /**
     * Find all enrollments by course id
     * @param courseId the id of the course
     * @return a list of enrollments
     */
    List<Enrollment> findAllByCourseId(Long courseId);

    /**
     * Find an enrollment by student record id and course id
     * @param studentRecordId the ACME Student Record ID of the student
     * @param courseId the id of the course
     * @return an optional of the enrollment if it exists
     */
    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId studentRecordId, Long courseId);
}
