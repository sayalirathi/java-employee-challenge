package com.example.rqchallenge.employees.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import com.example.rqchallenge.employees.bean.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.employees.bean.EmployeeResponse;
import com.example.rqchallenge.employees.bean.EmployeesResponse;
import com.example.rqchallenge.employees.bean.NewEmployee;
import com.example.rqchallenge.employees.bean.SavedEmployeeResponse;
import com.example.rqchallenge.employees.connection.DummyRestConnection;
import com.example.rqchallenge.employees.impl.EmployeeControllerImpl;
import com.example.rqchallenge.employees.service.IEmployeeService;
import com.example.rqchallenge.util.RQChallengeException;
import com.example.rqchallenge.util.RQChallengeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeServiceImpl implements IEmployeeService {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

  @Autowired
  private DummyRestConnection dummyRestConnection;

  @Override
  public List<Employee> getAllEmployees() throws RQChallengeException {
    try {
      logger.info("Executing Method to get all employees");
      ObjectMapper mapper = new ObjectMapper();

      String allEmployees = dummyRestConnection.getAllEmployees();
      EmployeesResponse employeesResponse = mapper.readValue(allEmployees, EmployeesResponse.class);

      if (employeesResponse.getStatus().equals("success")) {

        List<Employee> employees = employeesResponse.getEmployees();

        if (employees != null && !employees.isEmpty()) {
          logger.info("Successfully fetched Employees List from Dummy Service");
          return employees;
        
        } else {
          
          logger.error("Employees List was empty");
          throw new RQChallengeException("Employees are empty");
        }

      } else {

        String message = employeesResponse.getMessage();

        if (message != null && !message.isBlank()) {

          logger.error("Fetching Employees List from Dummy Service Failed with message " + message);

          throw new RQChallengeException(message);
        } else {
          logger.error("Fetching Employees List from Dummy Service Failed without any message ");
          throw new RQChallengeException(
              "Oops ! there was some error in Get All Employees API from Dummy Service");
        }

      }
    } catch (RestClientException | JsonProcessingException exception) {
      logger.error("Exception occurred while fetching Employees List from Dummy Service Failed "
          + exception.getMessage());
      throw new RQChallengeException(exception.getMessage());
    }
  }

  @Override
  public List<Employee> getEmployeesByNameSearch(String searchStr) throws RQChallengeException {
    logger.info("Fetching Employees List having string  " + searchStr);
    try {
      List<Employee> employees = getAllEmployees();

      List<Employee> searchedEmployees = employees.parallelStream()
          .filter(e -> e.getEmployeeName().toLowerCase().contains(searchStr.toLowerCase()))
          .collect(Collectors.toList());

      return searchedEmployees;
    } catch (RQChallengeException exception) {
      logger.error("Exception occurred while fetching Employees List having string  " + searchStr
          + " " + exception.getMessage());
      throw exception;
    }
  }

  @Override
  public Employee getEmployeeById(String employeeId) throws RQChallengeException {

    logger.info("Fetching Employee List having id  " + employeeId);
    try {
      ObjectMapper mapper = new ObjectMapper();

      String employee = dummyRestConnection.getEmployeeById(employeeId);
      EmployeeResponse employeeResponse = mapper.readValue(employee, EmployeeResponse.class);

      if (employeeResponse.getStatus().equals("success")) {

        if (employeeResponse.getEmployee() != null) {
          logger.info("Successfully fetched Employee from Dummy Service");
          
          return employeeResponse.getEmployee();
        } else {
          logger.error("Employee with ID " + employeeId + " is not found");
          throw new RQChallengeException("Employee with ID " + employeeId + " is not found");
        }
      } else {

        String message = employeeResponse.getMessage();

        if (message != null && !message.isBlank()) {
          logger.error("Fetching Employee from Dummy Service Failed with message " + message);

          throw new RQChallengeException(message);
        } else {

          logger.error("Fetching Employees List from Dummy Service Failed without any message ");
          
          throw new RQChallengeException(
              "Oops ! there was some error in Get Employee API from Dummy Service");
        }
      }

    } catch (RestClientException | JsonProcessingException exception) {
      
      logger.error("Exception occurred while fetching Employees List having id  " + employeeId
          + " " + exception.getMessage());
      throw new RQChallengeException(exception.getMessage());
    }
  }

  @Override
  public Integer getHighestSalaryOfEmployees() throws RQChallengeException {
    try {
      logger.info("Fetching Highest salary of employee ");
      List<Employee> employees = getAllEmployees();

      Optional<Employee> employee = employees.parallelStream()
          .sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).findFirst();
      return employee.get().getEmployeeSalary();
    } catch (RQChallengeException exception) {
      
      logger.error("Exception occurred while fetching Highest salary of employee "+ exception.getMessage());
      throw exception;
    }
  }

  @Override
  public List<String> getTop10HighestEarningEmployeeNames() throws RQChallengeException {

    try {
      logger.info("etching Top 10 highest earning employees ");
      List<Employee> employees = getAllEmployees();

      List<String> employeesList = employees.parallelStream()
          .sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).limit(10)
          .map(Employee::getEmployeeName).collect(Collectors.toList());

      return employeesList;
    } catch (RQChallengeException exception) {
      
      logger.error("Exception occurred while fetching Top 10 highest earning employees "+ exception.getMessage());
      throw exception;
    }
  }

  @Override
  public Employee createEmployee(Map<String, Object> employeeInput) throws RQChallengeException {
    try {
      logger.info("Creating Employee");
      validateEmployee(employeeInput);
      ObjectMapper mapper = new ObjectMapper();

      String employee = dummyRestConnection.createEmployee(employeeInput);

      SavedEmployeeResponse employeeResponse =
          mapper.readValue(employee, SavedEmployeeResponse.class);

      if (employeeResponse.getStatus().equals("success")) {

        NewEmployee newEmployee = employeeResponse.getEmployee();

        if (newEmployee != null) {
          logger.info("Employee was created successfully with id "+newEmployee.getId());
          return new Employee(newEmployee.getId(), newEmployee.getName(), newEmployee.getSalary(),
              newEmployee.getAge(), "");
        } else {
          logger.error("There was error while creating employee.");
          throw new RQChallengeException("Employee was not created");
        }

      } else {

        String message = employeeResponse.getMessage();

        if (message != null && !message.isBlank()) {
          logger.error("Employee Creation failed with message "+message);
          throw new RQChallengeException(message);
        } else {

          logger.error("Employee Creation failed without anny message "+message);
          throw new RQChallengeException(
              "Oops ! there was some error in Save Employee API from Dummy Service");
        }
      }

    } catch (RestClientException | JsonProcessingException exception) {
      logger.error("Exception occurred while creating employee "+ exception.getMessage());
      throw new RQChallengeException(exception.getMessage());
    }

  }

  private void validateEmployee(Map<String, Object> employeeInput) {

    try {
      Integer.parseInt(employeeInput.get("salary").toString());
    } catch (NumberFormatException numberFormatException) {
      logger.error("Salary is not in correct format"+employeeInput.get("salary").toString());
      throw new RQChallengeException("Salary is not in Integer format");
    }

    try {
      Integer.parseInt(employeeInput.get("age").toString());
    } catch (NumberFormatException numberFormatException) {
      logger.error("Age is not in correct format"+employeeInput.get("age").toString());
      throw new RQChallengeException("Age is not in Integer format");
    }



  }

  @Override
  public String deleteEmployeeById(String id) throws RQChallengeException {
    try {
      logger.info("Deleting Employee with id " + id);
      
      ObjectMapper mapper = new ObjectMapper();

      String deleteMessage = dummyRestConnection.deleteEmployee(id);

      DeleteEmployeeResponse deleteEmployeeResponse =
          mapper.readValue(deleteMessage, DeleteEmployeeResponse.class);

      if (deleteEmployeeResponse.getStatus().equals("success")) {

        String message = deleteEmployeeResponse.getMessage();
        if (message != null && !message.isBlank()) {
          logger.info("Employee was deleted successfully");
          return message;
        } else {
          logger.error("There was some error while deleting employee");
          throw new RQChallengeException("Employee was not deleted");
        }

      } else {

        String message = deleteEmployeeResponse.getMessage();

        if (message != null && !message.isBlank()) {
          logger.info("Employee deletion failed with message "+message);
          throw new RQChallengeException(message);
        } else {

          logger.info("Employee deletion failed without any message");
          throw new RQChallengeException(
              "Oops ! there was some error in Save Employee API from Dummy Service");
        }
      }
    } catch (RestClientException | JsonProcessingException exception) {
      logger.error("Exception occurred while deleting employee "+ exception.getMessage());
      throw new RQChallengeException(exception.getMessage());
    }


  }

}
