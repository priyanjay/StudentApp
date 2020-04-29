package com.student.junit;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.student.cucumber.serenity.StudentSerenitySteps;
import com.student.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

//@Concurrent // Two Threads per CPU core
@Concurrent(threads="4x") // 4 Threads per CPU
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("testdata/StudentInfo.csv")
public class CreateStudentDataDriventest extends TestBase{
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String course;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public StudentSerenitySteps getSteps() {
		return steps;
	}

	public void setSteps(StudentSerenitySteps steps) {
		this.steps = steps;
	}

	@Steps
	StudentSerenitySteps steps;
	
	@Title("Data Driven test for adding multiple students to the student app")
	@Test
	public void createMultipleStudents() {
		ArrayList<String> courses =new ArrayList<String>();
		courses.add(course);
		steps.createStudent(firstName, lastName, email, programme, courses);
	}
	
	
}
