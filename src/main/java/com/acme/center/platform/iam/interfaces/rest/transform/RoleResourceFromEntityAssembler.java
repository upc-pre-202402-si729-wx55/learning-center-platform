package com.acme.center.platform.iam.interfaces.rest.transform;

import com.acme.center.platform.iam.domain.model.entities.Role;
import com.acme.center.platform.iam.interfaces.rest.resources.RoleResource;

/**
 * Assembler to convert a Role entity to a RoleResource.
 * <p>
 *     This class is used to convert a Role entity to a RoleResource.
 * </p>
 */
public class RoleResourceFromEntityAssembler {
    /**
     * Converts a Role entity to a RoleResource.
     *
     * @param entity The Role entity to convert.
     * @return The RoleResource.
     */
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(
                entity.getId(),
                entity.getStringName());
    }
}
