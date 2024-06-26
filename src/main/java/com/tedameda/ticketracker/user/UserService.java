package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.user.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Service
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    DepartmentService departmentService;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, DepartmentService departmentService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    public UserEntity getUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return user;
    }

    public Role getRole(Long userId) {
        Role role = userRepository.findById(userId).get().getRole();
        return role;
    }

    public UserEntity createUser(CreateUserRequest request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDepartment(departmentService.getDepartment(request.getDepartment()));
        if (request.getRole() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(request.getRole());
        }
        return userRepository.save(user);
    }

    public UserEntity loginUser(UserLoginRequest request) {
        var user = getUser(request.getEmail());
        boolean isValidPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValidPassword) {
            throw new InvalidCredentialsException();
        }
        return user;
    }

    public UserEntity updatePassword(UpdatePasswordRequest request, Long userId) {
        var user = getUser(userId);
        if (!passwordEncoder.matches(request.getCurPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return userRepository.save(user);
    }

    public UserEntity changePermission(UpdateRoleRequest request) {
        UserEntity requestedFor = getUser(request.getEmail());
        requestedFor.setRole(request.getRole());
        return userRepository.save(requestedFor);
    }

    public UserEntity updateUser(Long userId, UpdateDetailsRequest request) {
        UserEntity user = getUser(userId);
        if (request.getCountryCode() != null) {
            user.setCountryCode(request.getCountryCode());
        }
        if (request.getMobileNumber() != null) {
            user.setMobileNumber(request.getMobileNumber());
        }
        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }
        if (request.getDepartment() != null) {
            user.setDepartment(departmentService.getDepartment(request.getDepartment()));
        }
        return userRepository.save(user);
    }

    //EXCEPTIONS
    static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(Long userId) {
            super("User with userId: " + userId + " not found");
        }

        public UserNotFoundException(String email) {
            super("User with email: " + email + " not found");
        }
    }

    static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException() {
            super("Invalid email or password");
        }
    }

    public static class UnauthorizedUserException extends IllegalArgumentException {
        public UnauthorizedUserException() {
            super("Unauthorized user");
        }
    }
}
