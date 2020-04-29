package com.student.cucumber.serenity;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.student.model.StudentClass;
import com.student.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {
	
	@Step("Creating a student with FirstName:{0}, LastName:{1}, Email:{2}, Programme:{3}, Courses:{4}")
	public ValidatableResponse createStudent(String firstName,String lastName,String email,String programme,List<String> courses) {
		StudentClass student=new StudentClass();
		student.setCourses(courses);
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setProgramme(programme);
		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec()).when().body(student).post().then();
	}
	
	@Step("Getting the student information by first name : {0}")
	public HashMap<String,Object> getStudentInfoByFirstName(String firstName){
		return SerenityRest.rest().given().when().get("/list").then().statusCode(200)
				.extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");
	}
	
	@Step("Updating the email:{0}, programme:{1}, firstName:{2} for the student id:{3})")
	public ValidatableResponse updateStudentInfo(String email,String programme,String firstName,int id) {
		StudentClass student=new StudentClass();
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setProgramme(programme);
		return SerenityRest.rest().given().contentType(ContentType.JSON).when().body(student).log().all().put("/"+id).then();
	}
	
	@Step("Deleting the student with id : {0}")
	public Response deleteStudent(int id) {
		return SerenityRest.rest().given().when().delete("/"+id);
	}
	
	@Step("Validating the student data with id : {0}")
	public ValidatableResponse validateStudent(int id) {
		return SerenityRest.rest().given().when().get("/"+id).then();
	}
	
}
