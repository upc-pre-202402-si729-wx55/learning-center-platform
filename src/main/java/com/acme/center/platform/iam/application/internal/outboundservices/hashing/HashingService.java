package com.acme.center.platform.iam.application.internal.outboundservices.hashing;

/**
 * HashingService
 * <p>
 *     Interface for hashing service.
 *     This service is used to encode and match passwords.
 * </p>
 */
public interface HashingService {
    /**
     * Encode a raw password.
     * @param rawPassword the raw password to encode
     * @return the encoded password
     */
    String encode(CharSequence rawPassword);

    /**
     * Match a raw password with an encoded password.
     * @param rawPassword the raw password to match
     * @param encodedPassword the encoded password to match
     * @return true if the raw password matches the encoded password, false otherwise
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
