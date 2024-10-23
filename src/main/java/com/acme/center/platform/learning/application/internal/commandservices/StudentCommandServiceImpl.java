package com.acme.center.platform.learning.application.internal.commandservices;

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

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        // Fetch profile by email from external service

        // TODO: Implement the logic to fetch the profile by email from external service.
        // If the profile is not found, create a new profile with command data.
        // If the profile is found, check if student exists in the repository.
        // If profile is empty, throw an exception.
        // Create a new student with the profile ID and save it in the repository.
        // Return the student record ID.
        // For now, return a new AcmeStudentRecordId until the logic is implemented.
        return new AcmeStudentRecordId();
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
