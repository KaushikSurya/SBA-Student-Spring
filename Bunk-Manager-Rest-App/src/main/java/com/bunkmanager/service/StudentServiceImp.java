package com.bunkmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunkmanager.dao.StudentRepo;
import com.bunkmanager.model.Gender;
import com.bunkmanager.model.Student;

@Service
public class StudentServiceImp implements StudentService {
	
	@Autowired
	public StudentRepo studentRepo;

	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void removeStudent(long id) {
		if(studentRepo.existsById(id)) {
			studentRepo.deleteById(id);
		}
	}

	@Override
	public void updateStudent(Student student) {
		if(studentRepo.existsById(student.getId())) {
			studentRepo.save(student);
		}
	}
	
	@Override
	public boolean exists(long id) {
		return studentRepo.existsById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return studentRepo.existsByName(name);
	}

	@Override
	public Student getStudent(long id) {
		Optional<Student> opt = studentRepo.findById(id);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student getStudentByName(String name) {
		return studentRepo.findByName(name);
	}

	@Override
	public List<Student> getStudentsByBranch(String branch) {
		return studentRepo.findAllByBranch(branch);
	}

	@Override
	public List<Student> getStudentsByGender(Gender gender) {
		return studentRepo.findAllByGender(gender);
	}

}
