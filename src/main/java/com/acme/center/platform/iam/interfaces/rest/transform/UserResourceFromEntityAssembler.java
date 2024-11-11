package com.acme.center.platform.iam.interfaces.rest.transform;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.interfaces.rest.resources.UserResource;

/**
 * Assembler to convert a User entity to a UserResource.
 * <p>
 *     This class is used to convert a User entity to a UserResource.
 * </p>
 */
public class UserResourceFromEntityAssembler {
    /**
     * Converts a User entity to a UserResource.
     *
     * @param entity The User entity to convert.
     * @return The UserResource.
     */
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getUsername(),
                RoleStringListFromEntityListAssembler.toResourceListFromEntitySet(entity.getRoles()));
    }
}
