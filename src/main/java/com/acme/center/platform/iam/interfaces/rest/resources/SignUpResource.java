package com.acme.center.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Sign-up resource.
 * <p>
 *     This resource is used to sign-up a new user.
 *     It contains the username, password and roles of the new user.
 *     The roles are used to assign the user to one or more roles.
 * </p>
 * @param username The username of the new user.
 *                 It must be unique.
 *                 It must not be null.
 *                 It must not be empty.
 * @param password The password of the new user.
 *                 It must not be null.
 *                 It must not be empty.
 * @param roles The roles of the new user.
 *              It must not be null.
 *              It must not be empty.
 *              It must contain at least one role.
 */
public record SignUpResource(String username, String password, List<String> roles) {
}
