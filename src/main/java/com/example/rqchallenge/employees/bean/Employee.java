package com.example.rqchallenge.employees.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("employee_name")
	private String employeeName;
	
	@JsonProperty("employee_salary")
	private Integer employeeSalary;
	
	@JsonProperty("employee_age")
	private Integer employeeAge;
	
	@JsonProperty("profile_image")
	private String profileImage;

	public Employee() {
		super();
	}

	

	public Employee(String id, String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.profileImage = profileImage;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Integer getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getProfileImage() {
		return profileImage;
	}



	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeAge == null) ? 0 : employeeAge.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((employeeSalary == null) ? 0 : employeeSalary.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((profileImage == null) ? 0 : profileImage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeAge == null) {
			if (other.employeeAge != null)
				return false;
		} else if (!employeeAge.equals(other.employeeAge))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (employeeSalary == null) {
			if (other.employeeSalary != null)
				return false;
		} else if (!employeeSalary.equals(other.employeeSalary))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary
				+ ", employeeAge=" + employeeAge + "]";
	}

}
