package com.example;

public abstract class Person {
	private String name="defaultName";

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}
}