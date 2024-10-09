package com.acme.center.platform.learning.domain.model.entities;


import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Getter
    private Long tutorialId;

    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem() {
    }

    public ProgressRecordItem(Enrollment enrollment, Long tutorialId) {
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isCompleted() {
        return ProgressStatus.COMPLETED.equals(this.status);
    }

    public boolean isInProgress() {
        return ProgressStatus.STARTED.equals(this.status);
    }

    public boolean isNotStarted() {
        return ProgressStatus.NOT_STARTED.equals(this.status);
    }

    public long calculateDaysElapsed() {
        if (isNotStarted()) return 0;
        var defaultTimeZone = java.time.ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant();
        var toDate = this.completedAt == null
                ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant()
                : this.completedAt.toInstant();
        return java.time.Duration.between(fromDate, toDate).toDays();
    }
}
