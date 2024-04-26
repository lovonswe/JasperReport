package com.example.jsperReport.controller;

import com.example.jsperReport.entity.Employee;
import com.example.jsperReport.repository.EmployeeRepository;
import com.example.jsperReport.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Employee>> getEmployeesPage(Pageable pageable, Sort sort){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return new ResponseEntity<>(employeeService.getEmployees(pageable), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getEmployeesList(Pageable pageable, Sort sort){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return new ResponseEntity<>(employeeService.getEmployees(pageable).getContent(), HttpStatus.OK);
    }
}
