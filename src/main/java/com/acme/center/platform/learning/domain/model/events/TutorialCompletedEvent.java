package com.acme.center.platform.learning.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Event that is published when a tutorial is completed.
 */
@Getter
public class TutorialCompletedEvent extends ApplicationEvent {
    private final Long enrollmentId;
    private final Long tutorialId;
    /**
     * Creates a new TutorialCompletedEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     * @param enrollmentId the enrollment ID
     * @param tutorialId the tutorial ID
     */
    public TutorialCompletedEvent(Object source, Long enrollmentId, Long tutorialId) {
        super(source);
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
    }
}
