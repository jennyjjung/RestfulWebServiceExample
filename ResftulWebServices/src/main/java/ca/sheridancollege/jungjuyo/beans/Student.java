package ca.sheridancollege.jungjuyo.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
	
	private static final long serialVersionUID = 292065859364113324L;

	private int id;
	private String name;
	private double grade;
	private String letterGrade;
	
	public Student(String name, double grade) {
		this.name = name;
		this.grade = grade;
		setLetterGrade();
	}

	public void setGrade(double grade) {
		this.grade = grade;
		setLetterGrade();
	}
	
	private void setLetterGrade() {
		if (grade > 80) letterGrade = "A";
		else if (grade > 70) letterGrade = "B";
		else if (grade > 60) letterGrade = "C";
		else if (grade > 50) letterGrade = "D";
		else letterGrade = "F";
	}


}

