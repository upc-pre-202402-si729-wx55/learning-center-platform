package com.acme.center.platform.learning.application.internal.queryservices;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.queries.*;
import com.acme.center.platform.learning.domain.services.EnrollmentQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {
    @Override
    public List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query) {
        return List.of();
    }

    @Override
    public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Enrollment> handle(GetAllEnrollmentsQuery query) {
        return List.of();
    }

    @Override
    public List<Enrollment> handle(GetAllEnrollmentsByCourseIdQuery query) {
        return List.of();
    }

    @Override
    public Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query) {
        return Optional.empty();
    }
}
