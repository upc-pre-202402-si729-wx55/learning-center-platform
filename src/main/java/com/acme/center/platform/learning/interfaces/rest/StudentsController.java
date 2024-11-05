package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.services.StudentCommandService;
import com.acme.center.platform.learning.domain.services.StudentQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.CreateStudentResource;
import com.acme.center.platform.learning.interfaces.rest.resources.StudentResource;
import com.acme.center.platform.learning.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The {@link StudentsController} class defines the RESTful API endpoints for the students.
 */
@RestController
@RequestMapping(value = "/api/v1/students", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Available Student Endpoints")
public class StudentsController {
    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    /**
     * Instantiates a new {@link StudentsController} instance.
     *
     * @param studentCommandService The {@link StudentCommandService} instance
     * @param studentQueryService   The {@link StudentQueryService} instance
     */
    public StudentsController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    /**
     * Create a new student
     *
     * @param resource The {@link CreateStudentResource} instance
     * @return The {@link StudentResource} resource for the created student, or a bad request response if the student was not created
     */
    @PostMapping
    @Operation(summary = "Create a new student", description = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Student not found")})
    public ResponseEntity<StudentResource> createStudent(CreateStudentResource resource) {
        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource);
        var acmeStudentRecordId = studentCommandService.handle(createStudentCommand);
        if (acmeStudentRecordId.studentRecordId().isEmpty()) return ResponseEntity.badRequest().build();
        var getStudentByAcmeStudentRecordIdQuery = new GetStudentByAcmeStudentRecordIdQuery(acmeStudentRecordId);
        var student = studentQueryService.handle(getStudentByAcmeStudentRecordIdQuery);
        if (student.isEmpty()) return ResponseEntity.notFound().build();
        var createdStudent = student.get();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(createdStudent);
        return new ResponseEntity<>(studentResource, HttpStatus.CREATED);
    }

    /**
     * Get student by Acme Student Record ID
     *
     * @param studentRecordId The Acme Student Record ID
     * @return The {@link StudentResource} resource for the student, or a not found response if the student was not found
     */
    @GetMapping("/{studentRecordId}")
    @Operation(summary = "Get student by Acme Student Record Id", description = "Get student by Acme Student Record Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")})
    public ResponseEntity<StudentResource> getStudentByAcmeStudentRecordId(@PathVariable String studentRecordId) {
        var acmeStudentRecordId = new AcmeStudentRecordId(studentRecordId);
        var getStudentByAcmeStudentRecordIdQuery = new GetStudentByAcmeStudentRecordIdQuery(acmeStudentRecordId);
        var student = studentQueryService.handle(getStudentByAcmeStudentRecordIdQuery);
        if (student.isEmpty()) return ResponseEntity.notFound().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }


}
