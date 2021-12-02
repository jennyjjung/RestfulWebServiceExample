package ca.sheridancollege.jungjuyo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.jungjuyo.beans.Student;

@SpringBootTest
@AutoConfigureMockMvc
class ResftulWebServicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	/******************************************/
	/* TESTING REST API - GET,POST,PUT,DELETE */
	/******************************************/
	
	@Test  // GET - get (read) a collection of students
	public void testGetCollection() throws Exception {
		
		String url = "/students";
		
		// Test a particular URL and retrieve the response from the request.
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		// Test that it was done successfully
		int status = mvcResult.getResponse().getStatus();
		assertEquals(status, 200);
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		Student[] students = new ObjectMapper().readValue(content, Student[].class);
		
		// Evaluate your result
		assertTrue(students.length > 0);
	}
	
	
	@Test  // GET - get (read) a single record that exists
	public void testGetSingleStudnt() throws Exception {
		
		// Specify a specific record to retrieve with a get request
		String url = "/students/2";
		
		// Test a particular URL and retrieve the response from the request.
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		// Test if it was successful
		int status = mvcResult.getResponse().getStatus();
		assertEquals(status, 200);
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		Student student = new ObjectMapper().readValue(content, Student.class);
		
		assertTrue(student != null);
	}
	
	
	@Test  // GET - check for a record that does not exist (null)
	public void testGetStudentDoesntExists() throws Exception {
		
		String url = "/students/100"; // not existing
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		
		// In this case, since my API return null if the record doesn't exist
		// JSON will return an empty String.
		assertTrue(content.equals(""));
	}
	
	
	@Test  // POST - test for adding a student using post
	public void testCreateStudent() throws Exception {
		
		String url = "/students";
		
		// Create a Student record to send
		Student student = new Student();
		student.setName("Jon");
		student.setGrade(66);
		
		// Convert it into JSON
		String studentJson = new ObjectMapper().writeValueAsString(student);
		
		// Test a particular URL and retrieve the response from the request.
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(studentJson))
				.andReturn();
	
		// Test if it was successful
		int status = mvcResult.getResponse().getStatus();
		assertEquals(status, 200);
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		
		// Check if the student was added
		assertTrue("Student was added".equals(content));
	}
	
	
	@Test // PUT - collection
	public void testReplaceStudentsCollection() throws Exception {
		
		String url = "/students";
		
		// Create a Student record to send
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student("Jon", 66);
		Student student2 = new Student("Nathan", 77);
		students.add(student1);
		students.add(student2);
		
		// Convert it into JSON
		String studentJson = new ObjectMapper().writeValueAsString(students);
		
		// Test a particular URL and retrieve the response from the request.
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(studentJson))
				.andReturn();
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		
		// Evaluate your result
		assertTrue("Students Added: 2".equals(content));
	}
	
	
	@Test // PUT - element
	public void testReplaceSingleStudent() throws Exception {
		
		String url = "/students/1";
		
		// Create a Student record to send
		Student student = new Student();
		student.setName("Jon");
		student.setGrade(66);
		
		// Convert it into JSON
		String studentJson = new ObjectMapper().writeValueAsString(student);
		
		// Test a particular URL and retrieve the response from the request.
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(studentJson))
				.andReturn();
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();

		// Evaluate your result
		assertTrue("Student Id 1 Updated".equals(content));
	}
	
	@Test // DELETE - collection
	public void testDeleteStudentsCollection() throws Exception {
		
		String url = "/students";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();

		// Evaluate your result
		assertTrue("Records deleted".equals(content));
	}
	
	@Test // DELETE - element
	public void testDeleteSingleStudent() throws Exception {
		
		String url = "/students/1";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		// Retrieve the JSON value and convert it into an appropriate type
		String content = mvcResult.getResponse().getContentAsString();

		// Evaluate your result
		assertTrue("Student ID 1 deleted".equals(content));
	}

}
