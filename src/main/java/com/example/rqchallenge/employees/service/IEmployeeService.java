package com.example.rqchallenge.employees.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.example.rqchallenge.employees.bean.Employee;
import com.example.rqchallenge.util.RQChallengeException;

@Component
public interface IEmployeeService {

	public List<Employee> getAllEmployees() throws RQChallengeException;
	

	public List<Employee> getEmployeesByNameSearch(String searchStr) throws RQChallengeException;


	public Employee getEmployeeById(String employeeId) throws RQChallengeException;


	public Integer getHighestSalaryOfEmployees() throws RQChallengeException;


	public List<String> getTop10HighestEarningEmployeeNames() throws RQChallengeException;


	public Employee createEmployee(Map<String, Object> employeeInput) throws RQChallengeException;


	public String deleteEmployeeById(String id) throws RQChallengeException;
}
