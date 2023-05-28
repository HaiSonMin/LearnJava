package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.Role;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        // Check if system doesn't exist admin
        Role role = this.roleRepository.findByName("ROLE_ADMIN");
        if(role == null) {
            role = checkRoleExist();
        }

        User newUser = new User();
        newUser.setName(userDto.getFirstName().trim()+" "+userDto.getLastName().trim());
        newUser.setEmail(userDto.getEmail());
        newUser.setRoles(Arrays.asList(role)); // Put role in the list
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        this.userRepository.save(newUser);

    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return this.roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {

        List<User> userList = this.userRepository.findAll();

        return userList.stream().map(user -> mapUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getName().split(" ")[0]);
        userDto.setLastName(user.getName().split(" ")[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
