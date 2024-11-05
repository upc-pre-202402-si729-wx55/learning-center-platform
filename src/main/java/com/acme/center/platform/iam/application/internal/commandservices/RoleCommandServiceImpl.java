package com.acme.center.platform.iam.application.internal.commandservices;

import com.acme.center.platform.iam.domain.model.commands.SeedRolesCommand;
import com.acme.center.platform.iam.domain.model.entities.Role;
import com.acme.center.platform.iam.domain.model.valueobjects.Roles;
import com.acme.center.platform.iam.domain.services.RoleCommandService;
import com.acme.center.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * RoleCommandServiceImpl
 * <p>
 *     Implementation of RoleCommandService.
 *     This class is responsible for handling the SeedRolesCommand and persisting the roles in the database.
 * </p>
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    /**
     * Constructor
     * @param roleRepository {@link RoleRepository} instance
     */
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // inherited javadoc
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role))
                roleRepository.save(new Role(Roles.valueOf(role.name())));
        });
    }
}
