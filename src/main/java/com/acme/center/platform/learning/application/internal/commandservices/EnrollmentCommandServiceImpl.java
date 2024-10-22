package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.domain.model.commands.*;
import com.acme.center.platform.learning.domain.services.EnrollmentCommandService;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {
    @Override
    public Long handle(RequestEnrollmentCommand command) {
        return 0L;
    }

    @Override
    public Long handle(ConfirmEnrollmentCommand command) {
        return 0L;
    }

    @Override
    public Long handle(RejectEnrollmentCommand command) {
        return 0L;
    }

    @Override
    public Long handle(CancelEnrollmentCommand command) {
        return 0L;
    }

    @Override
    public Long handle(CompleteTutorialForEnrollmentCommand command) {
        return 0L;
    }
}
