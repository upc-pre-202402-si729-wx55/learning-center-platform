package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.entities.Role;
import com.acme.center.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.acme.center.platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * RoleQueryService
 * <p>
 *     Service to handle role queries.
 * </p>
 */
public interface RoleQueryService {
    /**
     * Handle get all roles query.
     *
     * @param query the {@link GetAllRolesQuery} query
     * @return the list of {@link Role} entities
     */
    List<Role> handle(GetAllRolesQuery query);

    /**
     * Handle get role by name query.
     *
     * @param query the {@link GetRoleByNameQuery} query
     * @return the {@link Role} entity
     */
    Optional<Role> handle(GetRoleByNameQuery query);
}
