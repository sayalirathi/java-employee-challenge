package com.example.rqchallenge.employees.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavedEmployeeResponse extends DummyServiceResponse {

	@JsonProperty("data")
	private NewEmployee employee;

	public NewEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(NewEmployee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
		SavedEmployeeResponse other = (SavedEmployeeResponse) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}

	
}
