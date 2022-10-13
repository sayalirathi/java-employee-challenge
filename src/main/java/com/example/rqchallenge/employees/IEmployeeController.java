package com.example.rqchallenge.employees;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.util.RQChallengeException;

@RestController
public interface IEmployeeController {

  @GetMapping("/")
  ResponseEntity<List<Employee>> getAllEmployees() throws RQChallengeException;

  @GetMapping("/search/{searchString}")
  ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString)
      throws RQChallengeException;

  @GetMapping("/{id}")
  ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws RQChallengeException;

  @GetMapping("/highestSalary")
  ResponseEntity<Integer> getHighestSalaryOfEmployees() throws RQChallengeException;

  @GetMapping("/topTenHighestEarningEmployeeNames")
  ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws RQChallengeException;

  @PostMapping()
  ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput)
      throws RQChallengeException;

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteEmployeeById(@PathVariable String id) throws RQChallengeException;

}
