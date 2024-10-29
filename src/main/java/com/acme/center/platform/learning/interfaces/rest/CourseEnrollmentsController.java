package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.queries.GetAllEnrollmentsByCourseIdQuery;
import com.acme.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.EnrollmentResource;
import com.acme.center.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Course enrollments controller
 */
@RestController
@RequestMapping(value = "/api/v1/courses/{courseId}/enrollments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "All course related endpoints")
public class CourseEnrollmentsController {
    private final EnrollmentQueryService enrollmentQueryService;

    /**
     * Constructor
     *
     * @param enrollmentQueryService The {@link EnrollmentQueryService} service
     */
    public CourseEnrollmentsController(EnrollmentQueryService enrollmentQueryService) {
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Get all enrollments by course id
     *
     * @param courseId The course id
     * @return The list of {@link EnrollmentResource} enrollments
     */
    @Operation(summary = "Get all enrollments by course id")
    @Parameters({@Parameter(name = "courseId", description = "Course id", required = true)})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Enrollments found")})
    @GetMapping
    public ResponseEntity<List<EnrollmentResource>> getAllEnrollmentsByCourseId(@PathVariable Long courseId) {
        var getAllEnrollmentsByCourseIdQuery = new GetAllEnrollmentsByCourseIdQuery(courseId);
        var enrollments = enrollmentQueryService.handle(getAllEnrollmentsByCourseIdQuery);
        var enrollmentResources = enrollments.stream().map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(enrollmentResources);
    }
}
