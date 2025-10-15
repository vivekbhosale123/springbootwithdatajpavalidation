package com.vdb.service;

import com.vdb.model.Employee;
import com.vdb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        return employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword) != null;
    }

    @Override
    public Optional<Employee> findById(int empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int empId) {
        employeeRepository.deleteById(empId);
    }
}
