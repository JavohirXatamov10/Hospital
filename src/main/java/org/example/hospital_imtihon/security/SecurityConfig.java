package org.example.hospital_imtihon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(m->{
            m
                    .requestMatchers("login", "/css/**").permitAll()
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                    .requestMatchers("/super_admin/**").hasAnyRole("SUPER_ADMIN")
                    .requestMatchers("/doctor/**").hasRole("DOCTOR")
                    .requestMatchers("/patient/**").hasRole("PATIENT")
                    .anyRequest().authenticated();
        });
        http.formLogin(m->{
            m.loginPage("/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("phone");
        }
        );
        http.logout(m->{
            m
                    .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"));
                }
        ).userDetailsService(customUserDetailsService);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
