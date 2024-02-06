package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.user.dto.CreateUserRequest;
import com.tedameda.ticketracker.user.dto.UpdateRolesRequest;
import org.modelmapper.ModelMapper;
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
        return userRepository.save(user);
    }
    //TODO: Fix role update functionality
    public UserEntity addUserRole(Long userId, UpdateRolesRequest request){
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        if (user.getRole()==null){
            user.setRole(new HashSet<>());
        }
        user.getRole().addAll(request.getRoles());
        Set<String> s = user.getRole();
        for(var it :s ) {
            System.out.println(it);
        }
        return user;
    }

    public UserEntity removeUserRole(Long userId, UpdateRolesRequest request){
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        user.getRole().removeAll(request.getRoles());
        return user;
    }

    static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(Long userId){
            super("User with userId: "+userId+" not found");
        }
    }
}
