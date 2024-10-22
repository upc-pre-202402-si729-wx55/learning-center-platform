package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/**
 * Represents the Enrollment of a Student in a Course.
 * <p>
 *     An Enrollment is created when a Student requests to enroll in a Course.
 *     The Enrollment can be in one of the following states:
 *     <ul>
 *         <li>Requested: The Student has requested to enroll in the Course.</li>
 *         <li>Confirmed: The Student's request has been confirmed by the Admin.</li>
 *         <li>Rejected: The Student's request has been rejected by the Admin.</li>
 *         <li>Cancelled: The Student has cancelled the Enrollment.</li>
 *         <li>Completed: The Student has completed the Course.</li>
 * </p>
 */
@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private EnrollmentStatus status;

    @Embedded
    private ProgressRecord progressRecord;

    /**
     * Default Constructor
     */
    public Enrollment() {
    }

    /**
     * Constructor
     * @param studentRecordId The {@link AcmeStudentRecordId} Student Record ID
     * @param course The {@link Course} course
     */
    public Enrollment(AcmeStudentRecordId studentRecordId, Course course) {
        this.acmeStudentRecordId = studentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    /**
     * Confirm the Enrollment. It also initializes the Progress Record.
     */
    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
    }

    /**
     * Reject the Enrollment.
     */
    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
    }

    /**
     * Cancel the Enrollment.
     */
    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
    }

    /**
     * Get the Status of the Enrollment as a String.
     */
    public String getStatus() {
        return status.name().toLowerCase();
    }

    /**
     * Check if the Enrollment is in the Cancelled state.
     * @return true if the Enrollment is in the Cancelled state, false otherwise.
     */
    public boolean isCancelled() {
        return status == EnrollmentStatus.CANCELLED;
    }

    /**
     * Check if the Enrollment is in the Requested state.
     * @return true if the Enrollment is in the Confirmed state, false otherwise.
     */
    public boolean isRequested() {
        return status == EnrollmentStatus.REQUESTED;
    }

    /**
     * Check if the Enrollment is in the Confirmed state.
     * @return true if the Enrollment is in the Confirmed state, false otherwise.
     */
    public boolean isConfirmed() {
        return status == EnrollmentStatus.CONFIRMED;
    }

    /**
     * Check if the Enrollment is in the Rejected state.
     * @return true if the Enrollment is in the Rejected state, false otherwise.
     */
    public boolean isRejected() {
        return status == EnrollmentStatus.REJECTED;
    }

    /**
     * Mark the Tutorial as Completed. It also updates the Progress Record.
     * Finally, it emits a Tutorial Completed Event.
     * @param tutorialId The ID of the Tutorial that is completed.
     */
    public void completeTutorial(Long tutorialId) {
        // Update Progress Record
        this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
        // Emit the Tutorial Completed Event
        this.registerEvent(new TutorialCompletedEvent(this, this.getId(), tutorialId));
    }

}
