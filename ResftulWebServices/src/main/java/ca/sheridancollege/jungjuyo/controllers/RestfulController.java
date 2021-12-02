package ca.sheridancollege.jungjuyo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.jungjuyo.beans.Student;
import ca.sheridancollege.jungjuyo.repositories.StudentRepository;

@RestController
public class RestfulController {
	
	@Autowired
	private StudentRepository stuRepo;

	@GetMapping("/")
	public String iAmGroot() {
		return "home";
	}
	
	@GetMapping("/mew")
	public String[] meow() {
		String[] meow = {"meow", "meow", "meow"};
		return meow;		
	}
	
	@PostMapping("/woof")
	public String test() {
		return "woof";
	}
	
	
	// GET - collection - read	localhost:8080/students
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return stuRepo.getStudents();
	}
	
	//GET - element - read	localhost:8080/students/{id}/courses/{id}
	@GetMapping("/students/{id}")
	public Student getOneStudents(@PathVariable int id) {	
		
		if (stuRepo.getStudentById(id) != null) {
			return stuRepo.getStudentById(id);
		} else {
			return null;
		}
	}
	
	
	// POST - collection - add	localhost:8080/students
	// *** Not done for a single element *** POST - add
	@PostMapping(value="/students", headers={"Content-type=application/json"})
	public String addStudent(@RequestBody Student student) {
		stuRepo.addStudent(student);
		return "Student was added";
		// why do we need return statement? 
		// To ensure that the request was successfully sent with no errors
	}
	
	
	// PUT - collection - edit	localhost:8080/students
	@PutMapping(value="/students", headers={"Content-type=application/json"})
	public String replaceStudents(@RequestBody List<Student> studentList) {
		
		stuRepo.deleteStudents();
		stuRepo.resetCounter();
		
		for (Student s : studentList) {
			stuRepo.addStudent(s);
		}
		
		return "Students Added: " + stuRepo.getStudents().size();
	}
	
	
	// PUT - element - edit	localhost:8080/students/{id}
	@PutMapping(value="/students/{id}", headers={"Content-type=application/json"})
	public String replaceOneStudent(@PathVariable int id,
									@RequestBody Student student) {
		
//		stuRepo.deleteStudentById(id);
//		stuRepo.addStudent(student);
		stuRepo.updateStudent(student, id);
		
		return "Student Id " + id + " Updated";
	}
	
	
	// DELETE - collection - delete	localhost:8080/students
	@DeleteMapping(value="/students", headers={"Content-type=application/json"})
	public String deleteStudents() {
		
		stuRepo.deleteStudents();
		
		return "Records deleted";
	}
	
	
	// DELETE - element - delete localhost:8080/students/{id}
	@DeleteMapping(value="/students/{id}", headers={"Content-type=application/json"})
	public String deleteOneStudent(@PathVariable int id) {
		
		stuRepo.deleteStudentById(id);
		
		return "Student ID " + id + " deleted";
	}
}









