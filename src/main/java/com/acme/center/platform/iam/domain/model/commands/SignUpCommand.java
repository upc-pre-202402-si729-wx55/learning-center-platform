package com.acme.center.platform.iam.domain.model.commands;

import com.acme.center.platform.iam.domain.model.valueobjects.Roles;

import java.util.List;

/**
 * Command to sign up a new user.
 * <p>
 *     This command is used to sign up a new user in the system.
 *     It contains the username, password and roles of the new user.
 * </p>
 */
public record SignUpCommand(String username, String password, List<Roles> roles) {
}
