package com.bunkmanager.service;

import java.util.List;

import com.bunkmanager.model.Gender;
import com.bunkmanager.model.Student;

public interface StudentService {
	public void addStudent(Student Student);
	public void removeStudent(long id);
	public void updateStudent(Student Student);
	public boolean exists(long id);
	public boolean existsByName(String name);
	public Student getStudent(long id);
	public List<Student> getStudents();
	public Student getStudentByName(String name);
	public List<Student> getStudentsByBranch(String branch);
	public List<Student> getStudentsByGender(Gender gender);	
}