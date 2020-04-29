package com.student.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	@Test
	public void fetchAllStudents() {
//		RestAssured.given()
//		.when()
//		.get("/list")
//		.then()
//		.log().all()
//		.statusCode(200);
		
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log().all()
		.statusCode(200);
	}
	
	@Test
	public void validateFailedTest() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log().all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void validatePendingTest() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log().all()
		.statusCode(500);
	}
	
	@Ignore
	@Test
	public void validateSkippedTest() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log().all()
		.statusCode(500);
	}
	
	@Test
	public void thisIsATestWithError() {
		System.out.println("This is an error : "+(5/0));
	}
	
	@Test
	public void thisIsACompromisedTest() throws FileNotFoundException {
		File file=new File("E://Test.txt");
		FileReader reader=new FileReader(file);
		
	}
	
	@Manual
	@Test
	public void  thisIsAManualTest() {
		
	}

}
