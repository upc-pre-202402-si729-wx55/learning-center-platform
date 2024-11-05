package com.acme.center.platform.iam.domain.model.queries;

import com.acme.center.platform.iam.domain.model.valueobjects.Roles;

/**
 * Query to get a role by name.
 * <p>
 *     This query is used to get a role by its name.
 *     The name is a unique identifier for the role.
 * </p>
 */
public record GetRoleByNameQuery(Roles name) {
}
