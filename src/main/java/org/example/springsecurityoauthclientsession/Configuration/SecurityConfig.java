package org.example.springsecurityoauthclientsession.Configuration;

import org.example.springsecurityoauthclientsession.OAuth2.CustomClientRegistrationRepository;
import org.example.springsecurityoauthclientsession.Service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomClientRegistrationRepository clientRegistrationRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomClientRegistrationRepository customClientRegistrationRepository) throws Exception {
        http.
                csrf(csrf->csrf.disable())
                .formLogin(login->login.disable())
                .httpBasic(basic->basic.disable())
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
