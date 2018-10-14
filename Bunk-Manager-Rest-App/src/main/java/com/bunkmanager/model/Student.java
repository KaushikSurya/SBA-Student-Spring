package com.bunkmanager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="students")
public class Student {
	
	@Id 
	private long id;
	@NotEmpty(message="Name cant be empty")
	private String name;
	@NotEmpty(message="Branch cant be empty")
	private String branch;
	private double attendance;
	private double cgpa;
	@NotNull(message="Gender is required")
	private Gender gender;
	
	public Student() {	
	}
	
	public Student(long id, String name, String branch, double attendance, double cgpa, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.attendance = attendance;
		this.cgpa = cgpa;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public double getAttendance() {
		return attendance;
	}
	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
