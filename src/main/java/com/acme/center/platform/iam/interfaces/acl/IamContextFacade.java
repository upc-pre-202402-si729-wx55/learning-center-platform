package com.acme.center.platform.iam.interfaces.acl;

import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This interface provides the methods to interact with the IAM context.
 *     It provides the methods to create a user, fetch the username by userId.
 *     The implementation of this interface will be provided by the IAM module.
 *     This interface is used by the ACL module to interact with the IAM module.
 * </p>
 */
public interface IamContextFacade {
    /**
     * createUser
     * <p>
     *     This method is used to create a user in the IAM context.
     * </p>
     * @param username the username of the user
     * @param password the password of the user
     * @return the userId of the created user
     */
    Long createUser(String username, String password);
    /**
     * createUser
     * <p>
     *     This method is used to create a user in the IAM context.
     * </p>
     * @param username the username of the user
     * @param password the password of the user
     * @param roleNames the list of role names of the user
     * @return the userId of the created user
     */
    Long createUser(String username, String password, List<String> roleNames);

    /**
     * fetchUserIdByUsername
     * <p>
     *     This method is used to fetch the userId by username.
     * </p>
     * @param username the username of the user
     * @return the user id of the user if found, 0L otherwise
     */
    Long fetchUserIdByUsername(String username);

    /**
     * fetchUsernameByUserId
     * <p>
     *     This method is used to fetch the username by userId.
     * </p>
     * @param userId the userId of the user
     * @return the username of the user if found, empty string otherwise
     */
    String fetchUsernameByUserId(Long userId);
}
