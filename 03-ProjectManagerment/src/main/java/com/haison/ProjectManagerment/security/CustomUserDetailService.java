package com.haison.ProjectManagerment.security;

import com.haison.ProjectManagerment.entity.User;
import com.haison.ProjectManagerment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    // Can log in with email or username
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                                       .orElseThrow(()->new UsernameNotFoundException("User not exist!!"));

        // Get authorities by DB (ADMIN, MANAGER, USER)
        Set<GrantedAuthority> authorities = user.getRoles()
                                                .stream().map(role-> new SimpleGrantedAuthority(role.getName()))
                                                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
