package com.example.rqchallenge.employees.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeesResponse extends DummyServiceResponse{
	
	@JsonProperty("data")
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employee) {
		this.employees = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		return result;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeesResponse other = (EmployeesResponse) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeReponse [employee=" + employees + "]";
	}

}
