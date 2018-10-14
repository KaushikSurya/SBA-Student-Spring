package com.bunkmanager.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bunkmanager.model.Gender;
import com.bunkmanager.model.Student;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentApi {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<List<Student>> listStudentsAction() {
		ResponseEntity<List<Student>> resp = null;
		List<Student> students = studentService.getStudents();
		if (students != null && students.size() > 0)
			resp = new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
		return resp;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentAction(@PathVariable("id") long id) {
		ResponseEntity<Student> resp = null;
		Student student = studentService.getStudent(id);
		if (student == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(student, HttpStatus.OK);
		return resp;
	}

	@PostMapping
	public ResponseEntity<Student> addStudentAction(@RequestBody Student student) {
		ResponseEntity<Student> resp = null;
		if (student != null && !studentService.exists(student.getId())) {
			studentService.addStudent(student);
			resp = new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Student>(HttpStatus.CONFLICT);

		return resp;
	}

	@PutMapping
	public ResponseEntity<Student> updateStudentAction(@RequestBody Student student) {
		ResponseEntity<Student> resp = null;

		if (student != null && studentService.exists(student.getId())) {
			studentService.updateStudent(student);
			resp = new ResponseEntity<>(student, HttpStatus.OK);
		}
		else 
			resp = new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudentAction(@PathVariable("id") long id) {
		ResponseEntity<Void> resp = null;
		if (studentService.exists(id)) {
			studentService.removeStudent(id);
			resp = new ResponseEntity<>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<Student>> getAllStudents (
		@PathVariable("field") String fieldBy,
		@PathVariable("srchValue") String searchValue)
	{
		ResponseEntity<List<Student>> resp;
			switch(fieldBy){
			case "name":
				Student sbn= studentService.getStudentByName(searchValue);
				if(sbn!=null){
					resp=new ResponseEntity<>(Collections.singletonList(sbn),HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
				
			case "branch":				
				List<Student> sbb= studentService.getStudentsByBranch(searchValue);
				if(sbb!=null){
					resp=new ResponseEntity<>(sbb,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);}
				break;
				
			case "gender":
				Gender searchG = Gender.valueOf(searchValue);
				List<Student> sbg= studentService.getStudentsByGender(searchG);
				if(sbg!=null){
					resp=new ResponseEntity<>(sbg,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);}
				break;
				
			default:
				resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				break;	
		}
	
		
		return resp;
	}
}
