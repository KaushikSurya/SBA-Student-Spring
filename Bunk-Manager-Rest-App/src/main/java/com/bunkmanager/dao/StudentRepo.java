package com.bunkmanager.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunkmanager.model.Gender;
import com.bunkmanager.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	boolean existsByName(String name);
	Student findByName(String name);
	List<Student> findAllByBranch(String branch);
	List<Student> findAllByGender(Gender gender);
	boolean existsById(long id);
	void deleteById(long id);
	Optional<Student> findById(long id);
}
