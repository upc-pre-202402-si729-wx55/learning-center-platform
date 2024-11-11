package com.acme.center.platform.iam.domain.model.entities;

import com.acme.center.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Role
 * <p>
 *     Represents a role that can be assigned to a user.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    /**
     * Constructor
     * <p>
     *     Creates a new role with the given name.
     * </p>
     * @param name The name of the role.
     */
    public Role(Roles name) {
        this.name = name;
    }

    /**
     * Get Default Role
     * <p>
     *     Returns the default role.
     * </p>
     * @return The default role.
     */
    public static Role getDefaultRole() { return new Role(Roles.ROLE_USER); }

    /**
     * To Role From Name
     * <p>
     *     Converts a role name to a role.
     * </p>
     * @param name The name of the role.
     * @return The role.
     */
    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    /**
     * Validate Role Set
     * <p>
     *     Validates a set of roles. If the set is null or empty, the default role is returned.
     * </p>
     * @param roles The set of roles.
     * @return The set of roles.
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        return roles == null || roles.isEmpty() ? List.of(getDefaultRole()) : roles;
    }

    /**
     * Get String Name
     * <p>
     *     Returns the name of the role as a string.
     * </p>
     * @return The name of the role as a string.
     */
    public String getStringName() {
        return name.name();
    }
}
