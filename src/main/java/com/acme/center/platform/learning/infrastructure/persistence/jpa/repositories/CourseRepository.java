package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CourseRepository
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Find a course by its title
     * @param title the title of the course
     * @return an optional of the course
     */
    Optional<Course> findByTitle(String title);

    /**
     * Check if a course exists by its title
     * @param title the title of the course
     * @return true if the course exists, false otherwise
     */
    boolean existsByTitle(String title);

    /**
     * Check if a course exists by its title with an id different from the one provided
     * @param title the title of the course
     * @param id the id of the course to exclude
     * @return true if the course exists, false otherwise
     */
    boolean existsByTitleAndIdIsNot(String title, Long id);
}
