package com.example.rqchallenge.employees.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.rqchallenge.RqChallengeApplication;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;
import com.example.rqchallenge.util.RQChallengeException;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerImplTest {

  @Mock
  private IEmployeeService employeeService;

  @InjectMocks
  private EmployeeControllerImpl employeeControllerImpl = new EmployeeControllerImpl();

  String emlpoyees =
      "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":\"320800\",\"employee_age\":\"61\",\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":\"170750\",\"employee_age\":\"63\",\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"Ashton Cox\",\"employee_salary\":\"86000\",\"employee_age\":\"66\",\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"Cedric Kelly\",\"employee_salary\":\"433060\",\"employee_age\":\"22\",\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"Airi Satou\",\"employee_salary\":\"162700\",\"employee_age\":\"33\",\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}";

  @Test
  public void testGetAllEmployees() {

    
   Mockito.when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(new Employee("1", "Foo", 1500, 32, "") , new Employee("1", "XYZ", 1700, 35, "") ));
    
    ResponseEntity<List<Employee>> employees = employeeControllerImpl.getAllEmployees();
   Assertions.assertTrue(employees.getBody().size() == 2);

  }
  
  @Test
  public void testGetAllEmployeesWithNoRecords() {

   
   Mockito.when(employeeService.getAllEmployees()).thenReturn(new ArrayList<Employee>());
    
    ResponseEntity<List<Employee>> employees = employeeControllerImpl.getAllEmployees();
   Assertions.assertTrue(employees.getBody().size() == 0);

  }
  
  
  @Test
  public void testGetEmployeesByNameSearch() {
   
    Mockito.when(employeeService.getEmployeesByNameSearch(Mockito.anyString())).thenReturn(Arrays.asList(new Employee("1", "Foo", 1500, 32, "") ));

    ResponseEntity<List<Employee>> employees = employeeControllerImpl.getEmployeesByNameSearch("Foo");
   Assertions.assertTrue(employees.getBody().size() == 1);

  }
  
  @Test
  public void testGetEmployeesByNameSearchFailure() {
    Mockito.when(employeeService.getEmployeesByNameSearch(Mockito.anyString())).thenThrow(new RQChallengeException("Failed"));

    Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.getEmployeesByNameSearch("Cat");});

  }
  
  @Test
  public void testGetEmployeeById() {
    Mockito.when(employeeService.getEmployeeById(Mockito.anyString())).thenReturn(new Employee("1", "Foo", 1500, 32, "") );

    ResponseEntity<Employee> employees = employeeControllerImpl.getEmployeeById("1");
   Assertions.assertTrue(employees.getBody().getEmployeeName().equals("Foo"));

  }
  
  @Test
  public void testGetEmployeeByIdFailure() {
    Mockito.when(employeeService.getEmployeeById(Mockito.anyString())).thenThrow(new RQChallengeException("Failed"));;

    Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.getEmployeeById("2");});

  }
  
  @Test
  public void testGetHighestSalaryOfEmployees() {
    Mockito.when(employeeService.getHighestSalaryOfEmployees()).thenReturn(500);
    ResponseEntity<Integer> salary = employeeControllerImpl.getHighestSalaryOfEmployees();
   Assertions.assertTrue(salary.getBody() == 500);
  }
  
  @Test
  public void testGetHighestSalaryOfEmployeesFailure() {
    Mockito.when(employeeService.getHighestSalaryOfEmployees()).thenThrow(RQChallengeException.class);
    Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.getHighestSalaryOfEmployees();});
  }
  
  @Test
  public void testGetTopTenHighestEarningEmployeeNamesFailure() {
    Mockito.when(employeeService.getTop10HighestEarningEmployeeNames()).thenThrow(new RQChallengeException("Failed"));
    Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.getTopTenHighestEarningEmployeeNames();});
  }
  
  @Test
  public void testGetTopTenHighestEarningEmployeeNames() {
    Mockito.when(employeeService.getTop10HighestEarningEmployeeNames()).thenReturn(Arrays.asList("Foo" , "XYZ" ,"PQR" ));
    ResponseEntity<List<String>> employees = employeeControllerImpl.getTopTenHighestEarningEmployeeNames();
   Assertions.assertTrue(employees.getBody().size() == 3);
  }
  
  @Test
  public void testCreateEmployee() {
    
    Map<String, Object> employee = new HashMap<String, Object>();
    employee.put("name", "Foo User");
    employee.put("salary", "500");
    employee.put("age" , "30");
    Mockito.when(employeeService.createEmployee(employee)).thenReturn(new Employee("6" , "Foo User" , 500 , 30 ," "));
    ResponseEntity<Employee> employees = employeeControllerImpl.createEmployee(employee);
   Assertions.assertTrue(employees.getBody().getId().equals("6"));
    
    Mockito.when(employeeService.createEmployee(employee)).thenThrow(new RQChallengeException("Failed"));
   Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.createEmployee(employee);});
  }
  
  @Test
  public void testDeleteEmployee() {
    
    Mockito.when(employeeService.deleteEmployeeById(Mockito.anyString())).thenReturn("Success");
    ResponseEntity<String> employees = employeeControllerImpl.deleteEmployeeById("2");
   Assertions.assertTrue(employees.getBody().equals("Success"));
    
    Mockito.when(employeeService.deleteEmployeeById(Mockito.anyString())).thenThrow(new RQChallengeException("Failed"));
    Assertions.assertThrows(RQChallengeException.class, () -> {employeeControllerImpl.deleteEmployeeById("6");});
  }

}
