package com.example.rqchallenge;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.rqchallenge.employees.bean.DummyServiceResponse;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.employees.connection.DummyRestConnection;
import com.example.rqchallenge.employees.impl.EmployeeControllerImpl;
import com.example.rqchallenge.employees.service.IEmployeeService;
import com.example.rqchallenge.employees.service.impl.EmployeeServiceImpl;
import com.example.rqchallenge.util.RQChallengeException;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

  @Mock
  private DummyRestConnection dummyRestConnection;

  @InjectMocks
  private IEmployeeService employeeService = new EmployeeServiceImpl();

  public static final String EMPLOYEES =
      "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":\"320800\",\"employee_age\":\"61\",\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":\"170750\",\"employee_age\":\"63\",\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"Ashton Cox\",\"employee_salary\":\"86000\",\"employee_age\":\"66\",\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"Cedric Kelly\",\"employee_salary\":\"433060\",\"employee_age\":\"22\",\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"Airi Satou\",\"employee_salary\":\"162700\",\"employee_age\":\"33\",\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}";

  public static final String EMPLOYEES_FAILED =
      "{\"status\":\"failed\",\"message\":\"Employee Fetch Failed.\"}";

  public static final String EMPLOYEE =
      "{\"status\":\"success\",\"data\":{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":\"320800\",\"employee_age\":\"61\",\"profile_image\":\"\"},\"message\":\"Successfully! All records has been fetched.\"}";

  
  public static final String EMPLOYEE_CREATE =
      "{\"status\":\"success\",\"data\":{\"id\":123,\"name\":\"Tiger Nixon\",\"salary\":\"320800\",\"age\":\"61\"},\"message\":\"Successfully! All records has been fetched.\"}";

  public static final String EMPLOYEE_FAILED = "{\"status\":\"success\",\"data\":" + null
      + ",\"message\":\"Successfully! All records has been fetched.\"}";
  
  public static final String EMPLOYEE_DELETE = "{\"status\":\"success\",\"data\":" + null
      + ",\"message\":\"Employee Deleted.\"}";
  
  public static final String EMPLOYEE_DELETE_FAILED = "{\"status\":\"failed\",\"data\":" + null
      + ",\"message\":\"Employee Deletion Failed.\"}";

  @Test
  public void testGetAllEmployees() throws Exception {


    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES);

    List<Employee> employees = employeeService.getAllEmployees();
    Assertions.assertTrue(employees.size() == 5);

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.getAllEmployees();
    });

  }


  @Test
  public void testGetEmployeesByNameSearch() {

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES);

    List<Employee> employees = employeeService.getEmployeesByNameSearch("Co");
    Assertions.assertTrue(employees.size() == 1);

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.getEmployeesByNameSearch("ABC");
    });

  }


  @Test
  public void testGetEmployeeById() {

    Mockito.when(dummyRestConnection.getEmployeeById("1")).thenReturn(EMPLOYEE);

    Employee employees = employeeService.getEmployeeById("1");
    Assertions.assertTrue(employees.getEmployeeName().equals("Tiger Nixon"));

    Mockito.when(dummyRestConnection.getEmployeeById("2")).thenReturn(EMPLOYEE_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.getEmployeeById("2");
    });
  }


  @Test
  public void testGetHighestSalaryOfEmployees() {

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES);

    Integer salary = employeeService.getHighestSalaryOfEmployees();
    Assertions.assertEquals(salary, 433060);

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.getHighestSalaryOfEmployees();
    });
  }



  @Test
  public void testGetTopTenHighestEarningEmployeeNames() {
    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES);

    List<String> empList = employeeService.getTop10HighestEarningEmployeeNames();
    Assertions.assertEquals(empList.size(), 5);

    Mockito.when(dummyRestConnection.getAllEmployees()).thenReturn(EMPLOYEES_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.getTop10HighestEarningEmployeeNames();
    });
  }

  @Test
  public void testCreateEmployee() {

    Map<String, Object> employee = new HashMap<String, Object>();
    employee.put("name", "Foo User");
    employee.put("salary", "500");
    employee.put("age", "30");
    Mockito.when(dummyRestConnection.createEmployee(employee)).thenReturn(EMPLOYEE_CREATE);
    Employee employees = employeeService.createEmployee(employee);
    Assertions.assertTrue(employees.getId().equals("123"));

    Mockito.when(dummyRestConnection.createEmployee(employee)).thenReturn(EMPLOYEE_FAILED);
    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.createEmployee(employee);
    });
  }

  @Test
  public void testDeleteEmployee() {

    Mockito.when(dummyRestConnection.deleteEmployee(Mockito.anyString())).thenReturn(EMPLOYEE_DELETE);
    String message = employeeService.deleteEmployeeById("2");
    Assertions.assertTrue(message.equals("Employee Deleted."));

    Mockito.when(employeeService.deleteEmployeeById(Mockito.anyString())).thenReturn(EMPLOYEE_DELETE_FAILED);

    Assertions.assertThrows(RQChallengeException.class, () -> {
      employeeService.deleteEmployeeById("2");
    });
  }


}
