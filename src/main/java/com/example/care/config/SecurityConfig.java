// SecurityConfig.java
package com.example.care.config;

import com.example.care.entity.User;
import com.example.care.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
                .disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/login/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/google")
                .redirectionEndpoint(redirection -> redirection
                    .baseUri("/login/oauth2/code/google") // Explicitly set callback URI
                )
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(oauth2UserService())
                )
                .successHandler(successHandler())
                .failureHandler((request, response, exception) -> {
                    System.out.println("OAuth2 Login Failed: " + exception.getMessage());
                    exception.printStackTrace(); // Full stack trace for debugging
                    response.sendRedirect("/api/auth/error");
                })
            )
            .logout(logout -> logout
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/api/auth/logout-success")
                .permitAll()
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            );

        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return (userRequest) -> {
            System.out.println("Processing OAuth2 user request: " + userRequest.getClientRegistration().getRegistrationId());
            OAuth2User oauth2User = delegate.loadUser(userRequest);
            String email = oauth2User.getAttribute("email");
            String googleId = oauth2User.getAttribute("sub");
            System.out.println("OAuth2 User: email=" + email + ", googleId=" + googleId);

            User user = userRepository.findById(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setGoogleId(googleId);
                    return userRepository.save(newUser);
                });

            return new CustomOAuth2User(
                oauth2User.getAuthorities(),
                oauth2User.getAttributes(),
                "email",
                user
            );
        };
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            System.out.println("Authentication successful: " + authentication.getName());
            System.out.println("Authorities: " + authentication.getAuthorities());
            response.sendRedirect("/api/auth/success");
        };
    }
}