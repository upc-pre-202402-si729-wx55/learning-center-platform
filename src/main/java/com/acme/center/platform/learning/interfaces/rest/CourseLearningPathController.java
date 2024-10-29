package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.domain.services.CourseQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.LearningPathItemResource;
import com.acme.center.platform.learning.interfaces.rest.transform.LearningPathItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Course learning path controller
 */
@RestController
@RequestMapping(value = "/api/v1/courses/{courseId}/learning-path-items", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "All course related endpoints")
public class CourseLearningPathController {
    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    /**
     * Constructor
     *
     * @param courseCommandService The {@link CourseCommandService} service
     * @param courseQueryService   The {@link CourseQueryService} service
     */
    public CourseLearningPathController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    /**
     * Add tutorial to course learning path
     *
     * @param courseId   The course id
     * @param tutorialId The tutorial id
     * @return The {@link LearningPathItemResource} learning path item
     */
    @Operation(summary = "Add tutorial to course learning path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial added to course learning path"),
            @ApiResponse(responseCode = "404", description = "Tutorial or course not found")})
    @PostMapping("{tutorialId}")
    public ResponseEntity<LearningPathItemResource> addTutorialToCourseLearningPath(@PathVariable Long courseId, @PathVariable Long tutorialId) {
        courseCommandService.handle(new AddTutorialToCourseLearningPathCommand(courseId, tutorialId));
        var getLearningPathItemByCourseIdAndTutorialIdQuery = new GetLearningPathItemByCourseIdAndTutorialIdQuery(courseId, tutorialId);
        var learningPathItem = courseQueryService.handle(getLearningPathItemByCourseIdAndTutorialIdQuery);
        if (learningPathItem.isEmpty()) return ResponseEntity.notFound().build();
        var learningPathItemEntity = learningPathItem.get();
        var learningPathItemResource = LearningPathItemResourceFromEntityAssembler.toResourceFromEntity(learningPathItemEntity);
        return ResponseEntity.ok(learningPathItemResource);
    }
}
