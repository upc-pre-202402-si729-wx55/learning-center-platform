package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.center.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.center.platform.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * User query service.
 * <p>
 *     This service is responsible for handling user queries.
 *     It provides methods to handle queries to get users.
 * </p>
 */
public interface UserQueryService {

    /**
     * Handle get all users query.
     *
     * @param query The {@link GetAllUsersQuery} query.
     * @return The list of users.
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handle get user by id query.
     *
     * @param query The {@link GetUserByIdQuery} query containing the user id.
     * @return The user found.
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handle get user by username query.
     *
     * @param query The {@link GetUserByUsernameQuery} query containing the username.
     * @return The user found.
     */
    Optional<User> handle(GetUserByUsernameQuery query);
}
