package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;

    @Embedded
    private final LearningPath learningPath;

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        learningPath = new LearningPath();
    }

    public Course() {
        this(Strings.EMPTY, Strings.EMPTY);
    }

    public Course(CreateCourseCommand command) {
        this(command.title(), command.description());
    }

    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    public void addTutorialToLearningPath(Long tutorialId) {
        learningPath.addItem(this, tutorialId);
    }

    public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId) {
        learningPath.addItem(this, tutorialId, nextTutorialId);
    }

}
