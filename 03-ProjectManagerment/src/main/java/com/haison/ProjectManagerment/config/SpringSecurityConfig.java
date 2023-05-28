package com.haison.ProjectManagerment.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //Authorization
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable()
                        .authorizeHttpRequests((authorize)-> {
                            authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                            authorize.requestMatchers(HttpMethod.POST,"/api/**").hasAnyRole("ADMIN","MANAGER");
                            authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole("ADMIN","MANAGER");
                            // GET, PATCH accept ALL authorize (ADMIN, MANAGER, USER)
//                            authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","MANAGER","USER");
//                            authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","MANAGER","USER");
                            authorize.requestMatchers(HttpMethod.PATCH,"/api/**").permitAll();
                            authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
//                            authorize.anyRequest().authenticated();
                        })
                        .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService()  {
//        UserDetails haison = User.builder().username("haison")
//                                 .password(passwordEncoder().encode("haison")).roles("ADMIN").build();
//
//        UserDetails minhthu = User.builder().username("minhthu")
//                                  .password(passwordEncoder().encode("minhthu")).roles("MANAGER").build();
//
//        UserDetails hoainam = User.builder().username("hoainam")
//                                  .password(passwordEncoder().encode("hoainam")).roles("USER").build();
//
//        return new InMemoryUserDetailsManager(haison,minhthu,hoainam);
//
//    }
}
