package com.acme.center.platform.learning.application.internal.queryservices;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.queries.*;
import com.acme.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the EnrollmentQueryService interface.
 */
@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {
    private final EnrollmentRepository enrollmentRepository;

    /**
     * Constructor.
     *
     * @param enrollmentRepository the enrollment repository
     */
    public EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // inherited javadoc
    @Override
    public List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query) {
        return enrollmentRepository.findAllByAcmeStudentRecordId(query.studentRecordId());
    }

    // inherited javadoc
    @Override
    public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
        return enrollmentRepository.findById(query.enrollmentId());
    }

    // inherited javadoc
    @Override
    public List<Enrollment> handle(GetAllEnrollmentsQuery query) {
        return enrollmentRepository.findAll();
    }

    // inherited javadoc
    @Override
    public List<Enrollment> handle(GetAllEnrollmentsByCourseIdQuery query) {
        return enrollmentRepository.findAllByCourseId(query.courseId());
    }

    // inherited javadoc
    @Override
    public Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query) {
        return enrollmentRepository.findByAcmeStudentRecordIdAndCourseId(query.studentRecordId(), query.courseId());
    }
}
