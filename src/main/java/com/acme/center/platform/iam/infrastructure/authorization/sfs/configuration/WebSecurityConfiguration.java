package com.acme.center.platform.iam.infrastructure.authorization.sfs.configuration;

import com.acme.center.platform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.acme.center.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.acme.center.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * Web Security Configuration
 * <p>
 *     This class is the configuration for web security.
 *     It configures the security filters, authentication, authorization, and exception handling.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final BearerTokenService tokenService;
    private final BCryptHashingService hashingService;
    private final AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint;

    /**
     * Constructor
     * @param userDetailsService {@link UserDetailsService} User details service
     * @param tokenService {@link BearerTokenService} Bearer token service
     * @param hashingService {@link BCryptHashingService} Hashing service
     * @param unauthorizedRequestHandlerEntryPoint {@link AuthenticationEntryPoint} Unauthorized request handler entry point
     */
    public WebSecurityConfiguration(
            @Qualifier("defaultUserDetailsService")
            UserDetailsService userDetailsService,
            BearerTokenService tokenService,
            BCryptHashingService hashingService,
            AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandlerEntryPoint = unauthorizedRequestHandlerEntryPoint;
    }

    /**
     * Bearer Authorization Request Filter
     * @return {@link BearerAuthorizationRequestFilter} Bearer authorization request filter
     */
    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    /**
     * Authentication Manager
     * @param authenticationConfiguration {@link AuthenticationConfiguration} Authentication configuration
     * @return {@link AuthenticationManager} Authentication manager
     * @throws Exception If an error occurs
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Authentication Provider
     * @return {@link DaoAuthenticationProvider} Authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(hashingService);
        return provider;
    }

    /**
     * Password Encoder
     * @return {@link PasswordEncoder} Password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    /**
     * Security Filter Chain
     * <p>
     *     This method configures the security filter chain.
     *     It configures the security filters, authentication, authorization, and exception handling.
     *     It also configures the permitted request patterns.
     *     The permitted request patterns are the patterns that are allowed to be accessed without authentication.
     *     The other requests require authorization obtained from previous authentication.
     *     The authentication provides a Bearer token that is used to authenticate the user and access authorized resources.
     *     The Bearer token is extracted from the request and validated.
     *     If the token is valid, the user authentication is set in the security context.
     * </p>
     * @param http {@link HttpSecurity} Http security
     * @return {@link SecurityFilterChain} Security filter chain
     * @throws Exception If an error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var permittedRequestPatterns = new String[]{
                "/api/v1/authentication/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/webjars/**"
        };
        // Cross-Origin Resource Sharing configuration
        http.cors(configurer -> configurer.configurationSource(_ -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));
        // Cross-Site Request Forgery configuration
        http.csrf(customizer -> customizer.disable());
        // Exception handling configuration
        http.exceptionHandling(configurer -> configurer.authenticationEntryPoint(unauthorizedRequestHandlerEntryPoint));
        // Session management configuration
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Authorize requests configuration
        http.authorizeHttpRequests(configurer -> configurer.requestMatchers(permittedRequestPatterns).permitAll()
                        .anyRequest().authenticated());
        // Authentication configuration
        http.authenticationProvider(authenticationProvider());
        // Authorization configuration
        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
