package com.acme.center.platform.iam.domain.model.queries;

/**
 * Query to get a user by username.
 * <p>
 *     This query is used to get a user by its username.
 *     The username is a unique attribute for the user.
 * </p>
 */
public record GetUserByUsernameQuery(String username) {
}
