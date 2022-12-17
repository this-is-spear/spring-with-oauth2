package hello.springwithoauth2.conifg;

import hello.springwithoauth2.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable();

        http.authorizeHttpRequests()
            .antMatchers("/css/**", "/images/**", "/js/**", "/h2-console")
            .permitAll()
            .antMatchers("/api/vi").hasRole(Role.USER.name())
            .anyRequest().authenticated();

        http.logout().logoutSuccessUrl("/");

        http.oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);

        return http.build();
    }
}
