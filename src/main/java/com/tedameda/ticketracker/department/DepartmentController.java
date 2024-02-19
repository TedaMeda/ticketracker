package com.tedameda.ticketracker.department;

import com.tedameda.ticketracker.common.dto.ErrorResponseDTO;
import com.tedameda.ticketracker.department.dto.CreateDepartmentRequest;
import com.tedameda.ticketracker.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<DepartmentEntity>> getDepartments() {
        List<DepartmentEntity> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{department-name}")
    public ResponseEntity<DepartmentEntity> getDepartmentByID(@PathVariable(name = "department-name") String departmentName) {
        DepartmentEntity department = departmentService.getDepartment(departmentName);
        return ResponseEntity.ok(department);
    }

    @PostMapping("")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody CreateDepartmentRequest request) {
        var department = departmentService.createDepartment(request);
        URI uri = URI.create("/departments/" + department.getId());
        return ResponseEntity.created(uri).body(department);
    }

    @DeleteMapping("{department-name}")
    public ResponseEntity<String> deleteDepartment(@PathVariable(name = "department-name") String departmentName) {
        departmentService.deleteDepartment(departmentName);
        return ResponseEntity.ok("Department Deleted");
    }

    @ExceptionHandler({
            DepartmentService.DepartmentNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception ex) {
        String message;
        HttpStatus status;
        if (ex instanceof DepartmentService.DepartmentNotFoundException) {
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
