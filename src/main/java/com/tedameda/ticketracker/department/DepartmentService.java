package com.tedameda.ticketracker.department;

import com.tedameda.ticketracker.department.dto.CreateDepartmentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
    ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentEntity createDepartment(CreateDepartmentRequest request){
        var department = modelMapper.map(request, DepartmentEntity.class);
        return departmentRepository.save(department);
    }

    public DepartmentEntity getDepartment(String departmentName){
        var department=departmentRepository.findByName(departmentName).orElseThrow(
                ()->new DepartmentNotFoundException(departmentName)
        );
        return department;
    }

    public List<DepartmentEntity> getDepartments(){
        return departmentRepository.findAll();
    }

    public void deleteDepartment(String departmentName){
        departmentRepository.delete(
                departmentRepository.findByName(departmentName)
                        .orElseThrow(()->new DepartmentNotFoundException(departmentName))
        );
    }
    //EXCEPTIONS
    public static class DepartmentNotFoundException extends IllegalArgumentException{
        public DepartmentNotFoundException(String departmentName) {
            super("Department with name: " + departmentName + " not found");
        }
    }
}
