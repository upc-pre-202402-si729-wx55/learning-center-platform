package com.acme.center.platform.iam.application.internal.queryservices;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.center.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.center.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.acme.center.platform.iam.domain.services.UserQueryService;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserQueryServiceImpl
 * <p>
 *     Implementation of the {@link UserQueryService} interface.
 * </p>
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository the {@link UserRepository} instance
     */
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the {@link GetAllUsersQuery} query.
     * @param query the {@link GetAllUsersQuery} instance
     * @return the list of {@link User} instances
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * Handles the {@link GetUserByIdQuery} query.
     * @param query the {@link GetUserByIdQuery} instance
     * @return the {@link User} instance if found
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    /**
     * Handles the {@link GetUserByUsernameQuery} query.
     * @param query the {@link GetUserByUsernameQuery} instance
     * @return the {@link User} instance if found
     */
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
