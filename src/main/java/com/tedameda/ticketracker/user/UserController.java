package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.common.dto.ErrorResponseDTO;
import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.user.dto.CreateUserRequest;
import com.tedameda.ticketracker.user.dto.CreatedUserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<CreatedUserResponse> registerUser(@RequestBody CreateUserRequest request) {
        UserEntity savedUser = userService.createUser(request);
        URI savedUserURI = URI.create("/users/" + savedUser.getId());
        CreatedUserResponse userResponse = modelMapper.map(savedUser, CreatedUserResponse.class);
        return ResponseEntity.created(savedUserURI).body(userResponse);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            DepartmentService.DepartmentNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception ex) {
        String message;
        HttpStatus status;
        if (ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else if(ex instanceof DepartmentService.DepartmentNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else {
            message = "Internal Server Error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponseDTO response = ErrorResponseDTO.builder().message(message).build();
        return ResponseEntity.status(status).body(response);
    }

}
