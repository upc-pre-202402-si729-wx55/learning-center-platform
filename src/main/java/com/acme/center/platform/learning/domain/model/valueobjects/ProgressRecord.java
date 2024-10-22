package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProgressRecord is a value object that represents the progress of a student in a learning path.
 * <p>It contains a list of ProgressRecordItem objects that represent the progress of the student in each tutorial.
 * The ProgressRecord object is embedded in the Enrollment aggregate root.
 * The ProgressRecord object is responsible for managing the progress of the student in the learning path.</p>
 * @since 1.0.0
 */
@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    /**
     * Default constructor. Initializes an empty list of ProgressRecordItem objects.
     */
    public ProgressRecord() {
        this.progressRecordItems = new ArrayList<>();
    }

    /**
     * Initializes the progress record with the first tutorial in the learning path.
     *
     * @param enrollment    the {@link Enrollment} enrollment object
     * @param learningPath  the {@link LearningPath} learning path object
     *
     */
    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if (learningPath.isEmpty()) return;
        Long tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }

    /**
     * Returns the ProgressRecordItem object with the specified tutorial ID.
     *
     * @param tutorialId the tutorial ID
     * @return the {@link ProgressRecordItem} object
     */
    public ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    /**
     * Checks if there is a tutorial in progress.
     *
     * @return true if there is a tutorial in progress, false otherwise
     */
    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    /**
     * Starts the tutorial with the specified tutorial ID.
     *
     * @param tutorialId the tutorial ID
     */
    public void startTutorial(Long tutorialId) {
        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (Objects.isNull(progressRecordItem))
            throw new IllegalStateException("Tutorial with this ID is not in the progress record");
        if (progressRecordItem.isNotStarted()) progressRecordItem.start();
        else throw new IllegalStateException("Tutorial with this ID is already started or completed");
    }

    /**
     * Completes the tutorial with the specified tutorial ID.
     *
     * @param tutorialId    the tutorial ID
     * @param learningPath  the {@link LearningPath} learning path object
     */
    public void completeTutorial(Long tutorialId, LearningPath learningPath) {

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);

        if (Objects.isNull(progressRecordItem))
            throw new IllegalStateException("Tutorial with this ID is not in the progress record");

        if (progressRecordItem.isInProgress()) progressRecordItem.complete();
        else throw new IllegalStateException("Tutorial with this ID is not in progress");

        Long nextTutorialId = learningPath.getNextTutorialIdInLearningPath(tutorialId);

        if (Objects.nonNull(nextTutorialId)) {
            ProgressRecordItem nextProgressRecordItem = getProgressRecordItemWithTutorialId(nextTutorialId);
            if (Objects.isNull(nextProgressRecordItem)) {
                nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
                progressRecordItems.add(nextProgressRecordItem);
            }
        }
    }

    /**
     * Calculates the total number of days elapsed for the specified enrollment.
     *
     * @param enrollment the {@link Enrollment} enrollment object
     * @return the total number of days elapsed for the given enrollment
     */
    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed)
                .sum();
    }

}


