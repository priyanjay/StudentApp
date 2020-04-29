package com.student.junit;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.student.cucumber.serenity.StudentSerenitySteps;
import com.student.testbase.TestBase;
import com.student.utils.ReusableSpecifications;
import com.student.utils.TestUtils;

import jline.internal.Log;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDUsingSteps extends TestBase{

	static String firstName="Priyanjay"+TestUtils.getRandomValues();
	static String lastName="Singh"+TestUtils.getRandomValues();
	static String email="priyanjaysingh"+TestUtils.getRandomValues()+"@gmail.com";
	static String programme="CSE";
	public static int latestId;
	
	@Steps
	StudentSerenitySteps student;
	
	@Title("This test will create a new student using Serenity Steps")
	@Test
	public void test001() {
		ArrayList<String> courses=new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		student.createStudent(firstName, lastName, email, programme, courses).statusCode(201);
	}
	
	@Title("This test will get student info using Serenity Steps")
	@Test
	public void test002() {
		HashMap<String,Object> value=student.getStudentInfoByFirstName(firstName);
		assertThat(value,hasValue(firstName));
		latestId=(int)value.get("id");
	}
	
	@Title("This test will update the student info using Serenity Steps")
	@Test
	public void test003() {
		student.updateStudentInfo(email, programme, firstName, latestId).statusCode(200).spec(ReusableSpecifications.getGenericResponseSpec());
	}
	
	@Title("This test will delete one student using Serenity Steps")
	@Test
	public void test004() {
		int i=student.deleteStudent(latestId).getStatusCode();
		if(i==200) {
			Log.info("Student deleted");
		}else {
			Log.info("Student not deleted");
		}
		student.validateStudent(latestId).statusCode(404);
	}
	
}
