package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.common.dto.ErrorResponseDTO;
import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.security.JWTService;
import com.tedameda.ticketracker.user.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    ModelMapper modelMapper;
    JWTService jwtService;

    public UserController(UserService userService, ModelMapper modelMapper, JWTService jwtService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request) {
        UserEntity savedUser = userService.createUser(request);
        URI savedUserURI = URI.create("/users/" + savedUser.getId());
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(
                jwtService.createJwt(savedUser.getId())
        );
        return ResponseEntity.created(savedUserURI).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> longinUser(@RequestBody UserLoginRequest request) {
        var user = userService.loginUser(request);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setToken(jwtService.createJwt(user.getId()));
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request, @AuthenticationPrincipal UserEntity user) {
        var updatedUser = userService.updatePassword(request, user.getId());
        return ResponseEntity.ok("Password Updated");
    }

    @PutMapping("/update-permission")
    public ResponseEntity<String> updatePermission(@RequestBody UpdatePermissionRequest request, @AuthenticationPrincipal UserEntity user) {
        UserEntity updatedUser = userService.changePermission(user.getId(), request);
        return ResponseEntity.ok("Permission Updated");
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UpdateDetailsRequest request, @AuthenticationPrincipal UserEntity user) {
        UserEntity updatedUser = userService.updateUser(user.getId(), request);
        return ResponseEntity.ok("User details updated");
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidCredentialsException.class,
            UserService.UnauthorizedUserException.class,
            DepartmentService.DepartmentNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception ex) {
        String message;
        HttpStatus status;
        if (ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UserService.InvalidCredentialsException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof UserService.UnauthorizedUserException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof DepartmentService.DepartmentNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else {
            message = "Internal Server Error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponseDTO response = ErrorResponseDTO.builder().message(message).build();
        return ResponseEntity.status(status).body(response);
    }

}
