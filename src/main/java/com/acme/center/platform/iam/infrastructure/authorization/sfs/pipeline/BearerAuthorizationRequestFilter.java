package com.acme.center.platform.iam.infrastructure.authorization.sfs.pipeline;

import com.acme.center.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.acme.center.platform.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.acme.center.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * Filter to authenticate requests with Bearer token
 * <p>
 *     This filter will authenticate requests with Bearer token.
 *     It will extract the token from the request and validate it.
 *     If the token is valid, it will set the user authentication in the security context.
 *     The user authentication will be set with the user details from the token.
 * </p>
 */
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;
    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    /**
     * Constructor
     * @param tokenService {@link BearerTokenService} Bearer token service
     * @param userDetailsService {@link UserDetailsService} User details service
     */
    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Filter requests
     * <p>
     *     This method will filter the requests.
     *     It will extract the Bearer token from the request.
     *     If the token is valid, it will set the user authentication in the security context.
     *     The user authentication will be set with the user details from the token.
     *     If the token is not valid, it will log a warning.
     * </p>
     * @param request {@link HttpServletRequest} Request
     * @param response {@link HttpServletResponse} Response
     * @param filterChain {@link FilterChain} Filter chain
     * @throws ServletException If an error occurs
     * @throws IOException If an error occurs
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);
            if (Objects.nonNull(token) && tokenService.validateToken(token)) {
                var username = tokenService.getUsernameFromToken(token);
                var userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            } else {
                LOGGER.warn("Token is not valid");
            }
        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
