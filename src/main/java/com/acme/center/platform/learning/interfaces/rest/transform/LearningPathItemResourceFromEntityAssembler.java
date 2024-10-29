package com.acme.center.platform.learning.interfaces.rest.transform;

import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.interfaces.rest.resources.LearningPathItemResource;

/**
 * Assembler to convert a LearningPathItem entity to a LearningPathItemResource.
 */
public class LearningPathItemResourceFromEntityAssembler {
    /**
     * Converts a LearningPathItem entity to a LearningPathItemResource.
     *
     * @param entity The {@link LearningPathItem} entity to convert.
     * @return The {@link LearningPathItemResource} resource
     */
    public static LearningPathItemResource toResourceFromEntity(LearningPathItem entity) {
        return new LearningPathItemResource(entity.getId(), entity.getCourse().getId(), entity.getTutorialId());
    }
}
