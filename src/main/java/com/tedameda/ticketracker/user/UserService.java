package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.department.DepartmentRepository;
import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.user.dto.CreateUserRequest;
import com.tedameda.ticketracker.user.dto.UpdateRolesRequest;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Service
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    @Autowired
    DepartmentService departmentService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity getUser(Long id){
        UserEntity user = userRepository.getReferenceById(id);
        return user;
    }

    public UserEntity createUser(CreateUserRequest request){
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setDepartment(departmentService.findDepartmentByName(request.getDepartment()));
        return userRepository.save(user);
    }

    static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(Long userId){
            super("User with userId: "+userId+" not found");
        }
    }
}
