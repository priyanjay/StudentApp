package com.student.junit;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.student.model.StudentClass;
import com.student.testbase.TestBase;
import com.student.utils.TestUtils;

import ch.lambdaj.function.matcher.HasArgumentWithValue;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {
	
	static String firstName="Priyanjay"+TestUtils.getRandomValues();
	static String lastName="Singh"+TestUtils.getRandomValues();
	static String email="priyanjaysingh"+TestUtils.getRandomValues()+"@gmail.com";
	static String programme="CSE";
	public static int latestId;

	@Title("This test will create a new student")
	@Test
	public void test001() {
	//public void createStudent() {
		ArrayList<String> courses=new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		StudentClass student=new StudentClass();
		student.setCourses(courses);
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setProgramme(programme);
		SerenityRest.rest().given().contentType(ContentType.JSON).
		when().body(student).log().all().post()
		.then().log().all().statusCode(201);
	}
	
	@Title("Verify that student was added to the application")
	@Test
	public void test002() {
	//public void getStudent() {
		HashMap<String,Object> value=SerenityRest.rest().given().when().get("/list").then().statusCode(200)
		.extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");
		//System.out.println("The Value is : "+value);
		assertThat(value,hasValue(firstName));
		latestId=(int)value.get("id");
	}
	
	
	@Title("Update the First Name for the latest student")
	@Test
	public void test003() {
		StudentClass student =new StudentClass();
		student.setEmail(email);
		student.setProgramme(programme);
		student.setFirstName(firstName+"_Updated");
		SerenityRest.rest().given().contentType(ContentType.JSON).
		when().body(student).log().all().put("/"+latestId)
		.then().log().all().statusCode(200);
	}
	
	@Title("Delete the latest student and verify")
	@Test
	public void test004() {
		SerenityRest.rest().given().when().delete("/"+latestId);
		SerenityRest.rest().given().when().get("/"+latestId).then().log().all().statusCode(404);
	}
	
	
	
	
}
