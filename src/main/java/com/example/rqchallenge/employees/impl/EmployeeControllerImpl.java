package com.example.rqchallenge.employees.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;

@Component
public class EmployeeControllerImpl implements IEmployeeController {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerImpl.class);


  @Autowired
  private IEmployeeService employeeService;

  @Override
  public ResponseEntity<List<Employee>> getAllEmployees() {

    logger.info("Executing API to get all employees");
    List<Employee> employees = new ArrayList<Employee>();
    employees = employeeService.getAllEmployees();

    return ResponseEntity.ok().body(employees);

  }

  @Override
  public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {

    logger.info("Executing API to get employees contains string "+searchString);
    List<Employee> employees = employeeService.getEmployeesByNameSearch(searchString);
    return ResponseEntity.ok().body(employees);

  }

  @Override
  public ResponseEntity<Employee> getEmployeeById(String id) {

    logger.info("Executing API to get employee having id "+id);
    Employee employee = employeeService.getEmployeeById(id);
    return ResponseEntity.ok().body(employee);

  }

  @Override
  public ResponseEntity<Integer> getHighestSalaryOfEmployees() {

    logger.info("Executing API to get highest salary of employee");
    
    Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
    return ResponseEntity.ok().body(highestSalary);

  }

  @Override
  public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {

    logger.info("Executing API to get top 10 employees having highest salary ");
    
    List<String> employees = employeeService.getTop10HighestEarningEmployeeNames();
    return ResponseEntity.ok().body(employees);

  }

  @Override
  public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {

    logger.info("Executing API to create employee ");
    
    Employee employee = employeeService.createEmployee(employeeInput);
    return ResponseEntity.ok().body(employee);


  }

  @Override
  public ResponseEntity<String> deleteEmployeeById(String id) {

    logger.info("Executing API to delete employee with id "+id);
    
    String message = employeeService.deleteEmployeeById(id);
    return ResponseEntity.ok().body(message);

  }

}
