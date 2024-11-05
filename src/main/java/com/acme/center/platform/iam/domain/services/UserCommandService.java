package com.acme.center.platform.iam.domain.services;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.commands.SignInCommand;
import com.acme.center.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * User command service.
 * <p>
 *     This service is responsible for handling user commands.
 *     It provides methods to handle sign up and sign in commands.
 * </p>
 */
public interface UserCommandService {
    /**
     * Handle sign up command.
     *
     * @param command The {@link SignUpCommand} command, containing the user data.
     * @return The user created.
     */
    Optional<User> handle(SignUpCommand command);

    /**
     * Handle sign in command.
     *
     * @param command The {@link SignInCommand} command, containing the user data.
     * @return The user and the token.
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
