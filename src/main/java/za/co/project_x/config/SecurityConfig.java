package za.co.project_x.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import za.co.project_x.service.impl.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/register-user" ,"/css/**", "/js/**", "/images/**").permitAll()  // Allow login page and static resources
                .requestMatchers("/").authenticated()  // Require authentication for the home page
                .requestMatchers("/ideas").authenticated()
                .requestMatchers("/create").hasRole("ADMIN")
                .requestMatchers("/*/delete").hasRole("ADMIN")
                .anyRequest().authenticated()  // Require authentication for all other pages
                .and()
                .formLogin()
                .loginPage("/login")  // Custom login page URL
                .loginProcessingUrl("/login")  // URL where the login form submits
                .defaultSuccessUrl("/", false)  // Redirect to homepage after successful login
                .failureUrl("/login?error=true")  // Redirect to login page with error if login fails
                .permitAll()
                .and()
                .logout()
                .permitAll()  // Allow everyone to access the logout page
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error");  // Redirect to error page on access denied
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsServiceImpl; // Ensure you're returning your custom UserDetailsService
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsServiceImpl)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
