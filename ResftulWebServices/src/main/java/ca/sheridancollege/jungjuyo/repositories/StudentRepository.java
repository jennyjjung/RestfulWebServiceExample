package ca.sheridancollege.jungjuyo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.jungjuyo.beans.Student;

@Repository
public class StudentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	// add student
	public void addStudent(Student student) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO students (name, grade) VALUES (:name, :grade)";
		
		parameters.addValue("name", student.getName());
		parameters.addValue("grade", student.getGrade());
		
		jdbc.update(query, parameters);
	}
	
	
	
	// get student
	public List<Student> getStudents() {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM students";
		
		ArrayList<Student> students = (ArrayList<Student>)jdbc.query
				(query, parameters, new BeanPropertyRowMapper<Student>(Student.class));
		
		return students;
	}
	
	
	// get student by id
	public Student getStudentById(int id) {
		
		ArrayList<Student> students = new ArrayList<Student>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM students WHERE id=:id";
		parameters.addValue("id", id);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for (Map<String, Object> row : rows) {
			Student s = new Student();
			
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name"));
			s.setGrade((double)row.get("grade"));
	
			students.add(s);
		}
	
		if (students.size() == 1) {
			return students.get(0);
		} else {
			return null;
		}

	}
	
	// delete all students
	public void deleteStudents() {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "TRUNCATE TABLE students";
		jdbc.update(query, parameters);
		
//		String query = "DELETE FROM students";
//		jdbc.update(query,  new HashMap());
	}
	
	public void resetCounter() {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "ALTER TABLE students ALTER COLUMN id RESTART WITH 1";
		jdbc.update(query, parameters);
	}
	
	// delete student by id
	public void deleteStudentById(int id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE from students WHERE id=:id";
		
		parameters.addValue("id", id);
		
		jdbc.update(query, parameters);
	}
	
	// update student (PUT - element - mapping)
	public void updateStudent(Student student, int id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE students SET name=:name WHERE id=:id";
		
		parameters.addValue("id", id);
		parameters.addValue("name", student.getName());
		
		jdbc.update(query, parameters);
	}
	
	

}
