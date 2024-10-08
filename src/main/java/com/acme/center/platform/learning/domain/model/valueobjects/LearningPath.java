package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    public LearningPath() {
        this.learningPathItems = new ArrayList<>();
    }

    public void addItem(Course course, Long tutorialId, LearningPathItem nextItem) {
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        this.learningPathItems.add(learningPathItem);
    }

    public void addItem(Course course, Long tutorialId) {
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!this.learningPathItems.isEmpty())
            originalLastItem = this.getLastItemInLearningPath();
        this.learningPathItems.add(learningPathItem);
        if (originalLastItem != null) originalLastItem.updateNextItem(learningPathItem);
    }

    public LearningPathItem getLastItemInLearningPath() {
        return learningPathItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }

    public LearningPathItem getLearningPathItemWithTutorialId(Long tutorialId) {
        return learningPathItems.stream()
                .filter(item -> item.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    public void addItem(Course course, Long tutorialId, Long nextTutorialId) {
        LearningPathItem nextItem = this.getLearningPathItemWithTutorialId(nextTutorialId);
        this.addItem(course, tutorialId, nextItem);
    }

    public Long getFirstTutorialIdInLearningPath() {
        return this.learningPathItems.getFirst().getTutorialId();
    }

    public Long getNextTutorialIdInLearningPath(Long currentTutorialId) {
        LearningPathItem item = this.getLearningPathItemWithTutorialId(currentTutorialId);
        return item != null ? item.getNextItem().getTutorialId() : null;
    }

    public boolean isLastTutorialInLearningPath(Long currentTutorialId) {
        return getNextTutorialIdInLearningPath(currentTutorialId) == null;
    }

    public boolean isEmpty() {
        return this.learningPathItems.isEmpty();
    }

}
