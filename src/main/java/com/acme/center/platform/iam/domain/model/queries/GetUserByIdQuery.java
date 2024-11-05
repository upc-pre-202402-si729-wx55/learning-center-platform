package com.acme.center.platform.iam.domain.model.queries;

/**
 * Query to get a user by id.
 * <p>
 *     This query is used to get a user by its id.
 *     The id is a unique identifier for the user.
 * </p>
 */
public record GetUserByIdQuery(Long userId) {
}
