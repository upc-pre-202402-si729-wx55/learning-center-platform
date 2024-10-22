package com.acme.center.platform.learning.application.internal.queryservices;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.acme.center.platform.learning.domain.services.StudentQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentQueryServiceImpl implements StudentQueryService {
    @Override
    public Optional<Student> handle(GetStudentByProfileIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query) {
        return Optional.empty();
    }
}
