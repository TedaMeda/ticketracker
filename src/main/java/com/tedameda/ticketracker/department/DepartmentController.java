package com.tedameda.ticketracker.department;

import com.tedameda.ticketracker.department.dto.CreateDepartmentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    DepartmentService departmentService;
    ModelMapper modelMapper;

    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create-department")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody CreateDepartmentRequest request){
        var department =  departmentService.createDepartment(request);
        URI uri = URI.create("/departments/"+department.getId());
        return ResponseEntity.created(uri).body(department);
    }
}
