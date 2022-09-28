
package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
    //build create employee REST API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //build get all employee REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();
    }
    //build get employee by id REST API
    //http://localhost:8080/api/employees/1
    @GetMapping ("{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") long employeeId){
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }
  //build update employee REST API

    //http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public  ResponseEntity <Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }

    //build delete employee REST API
    //http://localhost:8080/api/employees/1
    @DeleteMapping ("{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable ("id") long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfuly", HttpStatus.OK);
    }
}
