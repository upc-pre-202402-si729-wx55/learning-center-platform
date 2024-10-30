package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.domain.services.CourseQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.CourseResource;
import com.acme.center.platform.learning.interfaces.rest.resources.CreateCourseResource;
import com.acme.center.platform.learning.interfaces.rest.resources.UpdateCourseResource;
import com.acme.center.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * CoursesController
 */
@RestController
@RequestMapping(value = "/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "All course related endpoints")
public class CoursesController {
    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    /**
     * Constructor
     * @param courseCommandService The {@link CourseCommandService} instance
     * @param courseQueryService The {@link CourseQueryService} instance
     */
    public CoursesController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    /**
     * Create a new course
     * @param createCourseResource The {@link CreateCourseResource} instance
     * @return A {@link CourseResource} resource for the created course
     */
    @Operation(summary = "Create a new course")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    @PostMapping
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource createCourseResource) {
        var createCourseCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(createCourseResource);
        var courseId = courseCommandService.handle(createCourseCommand);
        if (courseId == null || courseId == 0L) return ResponseEntity.badRequest().build();
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.notFound().build();
        var courseEntity = course.get();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(courseEntity);
        return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
    }

    /**
     * Get a course by id
     * @param courseId The course id
     * @return A {@link CourseResource} resource for the course
     */
    @Operation(summary = "Get a course by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.notFound().build();
        var courseEntity = course.get();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(courseEntity);
        return ResponseEntity.ok(courseResource);
    }

    /**
     * Get all courses
     * @return A list of {@link CourseResource} resources for all courses
     */
    @Operation(summary = "Get all courses")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Courses found"),
            @ApiResponse(responseCode = "404", description = "Courses not found")})
    @GetMapping
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var getAllCoursesQuery = new GetAllCoursesQuery();
        var courses = courseQueryService.handle(getAllCoursesQuery);
        var courseResources = courses.stream()
                .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(courseResources);
    }

    /**
     * Update a course
     * @param courseId The course id
     * @param updateCourseResource The {@link UpdateCourseResource} instance
     * @return A {@link CourseResource} resource for the updated course
     */
    @Operation(summary = "Update a course")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Course updated"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    @PutMapping
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource updateCourseResource) {
        var updateCourseCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, updateCourseResource);
        var updatedCourse = courseCommandService.handle(updateCourseCommand);
        if (updatedCourse.isEmpty()) return ResponseEntity.notFound().build();
        var updatedCourseEntity = updatedCourse.get();
        var updatedCourseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(updatedCourseEntity);
        return ResponseEntity.ok(updatedCourseResource);
    }

    /**
     * Delete a course
     * @param courseId The course id
     * @return A message indicating the course was deleted
     */
    @Operation(summary = "Delete a course")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Course deleted"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var deleteCourseCommand = new DeleteCourseCommand(courseId);
        courseCommandService.handle(deleteCourseCommand);
        return ResponseEntity.ok("Course with given id successfully deleted");
    }

}
