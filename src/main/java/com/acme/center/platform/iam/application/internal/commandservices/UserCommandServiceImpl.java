package com.acme.center.platform.iam.application.internal.commandservices;

import com.acme.center.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.center.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.domain.model.commands.SignInCommand;
import com.acme.center.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.center.platform.iam.domain.model.entities.Role;
import com.acme.center.platform.iam.domain.services.UserCommandService;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserCommandServiceImpl
 * <p>
 *     Implementation of UserCommandService.
 *     This class is responsible for handling the SignUpCommand and SignInCommand and persisting the user in the database.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    /**
     * Constructor
     * @param userRepository {@link UserRepository} instance
     * @param hashingService {@link HashingService} instance
     * @param tokenService {@link TokenService} instance
     * @param roleRepository {@link RoleRepository} instance
     */
    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    // inherited javadoc
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    // inherited javadoc
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        var existingUser = user.get();
        if(!hashingService.matches(command.password(), existingUser.getPassword())) throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(existingUser.getUsername());
        return Optional.of(ImmutablePair.of(existingUser, token));
    }
}
