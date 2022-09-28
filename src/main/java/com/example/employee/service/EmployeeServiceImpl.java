package com.example.employee.service;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional <Employee> employee = employeeRepository.findById(id);

     /*lambda expression
     return employeeRepository.findBy(id).orElseThrow(()->
             new ResourceNotFoundException("Employee", "Id", "Id"); */

        if (employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee","Id","id");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //check if the employee with the given id is in the DB/not

        Employee exstingEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee","id","id"));
        exstingEmployee.setFirstName(employee.getFirstName());
        exstingEmployee.setLastName(employee.getLastName());
        exstingEmployee.setEmail(employee.getEmail());

        //save existing employee to DB

        employeeRepository.save(exstingEmployee);

        return exstingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //check if employee Id exists in the DB/not
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                        ("Employee","Id","id"));
        employeeRepository.deleteById(id);
    }

}
