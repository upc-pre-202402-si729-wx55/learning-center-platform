package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.commands.CancelEnrollmentCommand;
import com.acme.center.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.center.platform.learning.domain.model.commands.RejectEnrollmentCommand;
import com.acme.center.platform.learning.domain.model.queries.GetAllEnrollmentsQuery;
import com.acme.center.platform.learning.domain.model.queries.GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery;
import com.acme.center.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.EnrollmentResource;
import com.acme.center.platform.learning.interfaces.rest.resources.RequestEnrollmentResource;
import com.acme.center.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.RequestEnrollmentCommandFromResourceAssembler;
import com.acme.center.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Enrollments Controller
 */
@RestController
@RequestMapping(value = "/api/v1/enrollments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Enrollments", description = "Available Enrollment Endpoints")
public class EnrollmentsController {
    private final EnrollmentCommandService enrollmentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    /**
     * Constructor
     *
     * @param enrollmentCommandService Enrollment Command Service
     * @param enrollmentQueryService   Enrollment Query Service
     */
    public EnrollmentsController(EnrollmentCommandService enrollmentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.enrollmentCommandService = enrollmentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Request Enrollment
     *
     * @param resource The {@link RequestEnrollmentResource} object containing the request data
     * @return The {@link EnrollmentResource} Resource of the requested enrollment
     */
    @PostMapping
    @Operation(summary = "Request Enrollment", description = "Request an enrollment for a student in a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollment requested successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Enrollment not found")})
    public ResponseEntity<EnrollmentResource> requestEnrollment(@RequestBody RequestEnrollmentResource resource) {
        var requestEnrollmentCommand = RequestEnrollmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var enrollmentId = enrollmentCommandService.handle(requestEnrollmentCommand);
        if (enrollmentId == null || enrollmentId.equals(0L)) return ResponseEntity.badRequest().build();
        var getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery = new GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(
                requestEnrollmentCommand.studentRecordId(), requestEnrollmentCommand.courseId());
        var enrollment = enrollmentQueryService.handle(getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery);
        if (enrollment.isEmpty()) return ResponseEntity.notFound().build();
        var requestedEnrollment = enrollment.get();
        var enrollmentResource = EnrollmentResourceFromEntityAssembler.toResourceFromEntity(requestedEnrollment);
        return ResponseEntity.ok(enrollmentResource);
    }

    /**
     * Confirm Enrollment
     *
     * @param enrollmentId The enrollment ID
     * @return The {@link MessageResource} Resource of the confirmation, or a bad request response
     */
    @PostMapping("/{enrollmentId}/confirmations")
    @Operation(summary = "Confirm Enrollment", description = "Confirm an enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<MessageResource> confirmEnrollment(@PathVariable Long enrollmentId) {
        var confirmEnrollmentCommand = new ConfirmEnrollmentCommand(enrollmentId);
        var confirmedEnrollment = enrollmentCommandService.handle(confirmEnrollmentCommand);
        if (confirmedEnrollment == null || confirmedEnrollment.equals(0L)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new MessageResource("Enrollment confirmed successfully"));
    }

    /**
     * Handles a request to reject an enrollment.
     *
     * @param enrollmentId The enrollment ID.
     * @return MessageResource with the enrollment ID.
     * @see MessageResource
     */
    @PostMapping("/{enrollmentId}/rejections")
    @Operation(summary = "Reject Enrollment", description = "Reject an enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment rejected successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<MessageResource> rejectEnrollment(@PathVariable Long enrollmentId) {
        var rejectEnrollmentCommand = new RejectEnrollmentCommand(enrollmentId);
        enrollmentCommandService.handle(rejectEnrollmentCommand);
        return ResponseEntity.ok(new MessageResource("Rejected Enrollment ID: " + enrollmentId));
    }

    /**
     * Handles a request to cancel an enrollment.
     *
     * @param enrollmentId The enrollment ID.
     * @return MessageResource with the enrollment ID.
     * @see MessageResource
     *
     */
    @PostMapping("/{enrollmentId}/cancellations")
    @Operation(summary = "Cancel Enrollment", description = "Cancel an enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment cancelled successfully")})
    public ResponseEntity<MessageResource> cancelEnrollment(@PathVariable Long enrollmentId) {
        var cancelEnrollmentCommand = new CancelEnrollmentCommand(enrollmentId);
        enrollmentCommandService.handle(cancelEnrollmentCommand);
        return ResponseEntity.ok(new MessageResource("Cancelled Enrollment ID: "+ enrollmentId));
    }

    /**
     * Gets all the enrollments.
     *
     * @return The list of all the enrollment resources available.
     * @see EnrollmentResource
     */
    @GetMapping
    @Operation(summary = "Get all enrollments", description = "Get all enrollments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollments found")})
    public ResponseEntity<List<EnrollmentResource>> getAllEnrollments() {
        var getAllEnrollmentsQuery = new GetAllEnrollmentsQuery();
        var enrollments = enrollmentQueryService.handle(getAllEnrollmentsQuery);
        var enrollmentResources = enrollments.stream().map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(enrollmentResources);
    }
}
