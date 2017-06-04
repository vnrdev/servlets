package com.example;

public class Employee extends Person {
	private int employeeID=0;
	
	public int getEmployeeID(){
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID){
		this.employeeID=employeeID;
	}
}