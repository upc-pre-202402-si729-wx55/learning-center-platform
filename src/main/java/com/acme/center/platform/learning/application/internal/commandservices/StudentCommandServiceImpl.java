package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.center.platform.learning.domain.exceptions.StudentMetricsUpdateException;
import com.acme.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.services.StudentCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalProfileService externalProfileService) {
        this.studentRepository = studentRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        // Fetch profile by email from external service

        var profileId = externalProfileService.fetchProfileByEmail(command.email());
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.street(),
                    command.number(),
                    command.city(),
                    command.postalCode(),
                    command.country());
        } else {
            studentRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Student already exists in the system.");
            });
        }

        if (profileId.isEmpty()) {
            throw new IllegalArgumentException("Unable to create student profile.");
        }

        // Create a new student with the profile data.
        var student = new Student(profileId.get());
        studentRepository.save(student);
        return student.getAcmeStudentRecordId();
    }

    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
        var result = studentRepository.findByAcmeStudentRecordId(command.studentRecordId());
        if (result.isEmpty()) {
            throw new StudentNotFoundException(command.studentRecordId());
        }
        // Update student metrics
        Student student = result.get();
        try {
            student.updateMetricsOnTutorialCompleted();
            studentRepository.save(student);
            return student.getAcmeStudentRecordId();
        } catch (Exception e) {
            throw new StudentMetricsUpdateException(command.studentRecordId(), e.getMessage());
        }

    }
}
