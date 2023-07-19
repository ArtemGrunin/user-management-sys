package ua.com.usermanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.usermanagementsystem.model.RoleName;
import ua.com.usermanagementsystem.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String ADMIN = RoleName.ADMIN.name();
    private static final String USER = RoleName.USER.name();
    private final UserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST, "/users/new")
                                .hasRole(ADMIN)
                                .requestMatchers("/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/**")
                                .hasAnyRole(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/roles")
                                .hasAnyRole(USER, ADMIN)
                                .requestMatchers("/users/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.PUT, "/users/{id}/**")
                                .hasRole(ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/users/{id}")
                                .hasRole(ADMIN)
                                .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsService)
                .build();
    }
}
