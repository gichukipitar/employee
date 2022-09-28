package com.example.employee.service;
import com.example.employee.model.Employee;

import java.util.List;


public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List <Employee> getAllEmployee();
    Employee getEmployeeById (long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee (long id);

}
