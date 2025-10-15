package com.vdb.controller;

import com.vdb.exception.RecordNotFoundException;
import com.vdb.model.Employee;
import com.vdb.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        log.info("Trying to save employee data: {}", employee.getEmpFirstName());
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findById/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/changeAddress/{empId}/{empAddress}")
    public ResponseEntity<Employee> changeAddress(@PathVariable int empId, @PathVariable String empAddress) {
        Employee employee = employeeService.findById(empId)
                .orElseThrow(() -> new RecordNotFoundException("Employee Not Exists"));

        employee.setEmpAddress(empAddress);
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @Valid @RequestBody Employee employee) {
        Employee employeeFromDb = employeeService.findById(empId)
                .orElseThrow(() -> new RecordNotFoundException("Employee Not Exists"));

        employeeFromDb.setEmpFirstName(employee.getEmpFirstName());
        employeeFromDb.setEmpLastName(employee.getEmpLastName());
        employeeFromDb.setEmpAddress(employee.getEmpAddress());
        employeeFromDb.setEmpContactNumber(employee.getEmpContactNumber());
        employeeFromDb.setEmpSalary(employee.getEmpSalary());
        employeeFromDb.setEmpEmailId(employee.getEmpEmailId());
        employeeFromDb.setEmpDob(employee.getEmpDob());
        employeeFromDb.setEmpPassword(employee.getEmpPassword());

        return new ResponseEntity<>(employeeService.update(employeeFromDb), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> delete(@PathVariable int empId) {
        Employee employee = employeeService.findById(empId)
                .orElseThrow(() -> new RecordNotFoundException("Employee Not Exists"));

        employeeService.deleteById(empId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}
