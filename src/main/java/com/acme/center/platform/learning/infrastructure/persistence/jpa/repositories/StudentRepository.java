package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * StudentRepository
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a student by its ACME Student Record ID
     * @param studentRecordId the ACME Student Record ID of the student
     * @return an optional of the student
     */
    Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

    /**
     * Find a student by its profile ID
     * @param profileId the profile ID of the student
     * @return an optional of the student
     */
    Optional<Student> findByProfileId(ProfileId profileId);

    /**
     * Check if a student exists by its ACME Student Record ID
     * @param studentRecordId the ACME Student Record ID of the student
     * @return true if the student exists, false otherwise
     */
    boolean existsByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

}
