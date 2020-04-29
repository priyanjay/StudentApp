package com.student.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.student.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@RunWith(SerenityRunner.class)
public class TaggedTest extends TestBase{

	@WithTag("studentFeature:NEGATIVE")
	@Title("Provide a 405 status code with invalid HTTP method is used to access a resource")
	@Test
	public void invalidMethod() {
		SerenityRest.rest().given().when().post("/list").then().statusCode(405).log().all();
	}
	
	@WithTag("studentFeature:POSITIVE")
	@Title("This test will verify when 200 status code is returned for GET request")
	@Test
	public void verifyIfStatusCodeIs200() {
		SerenityRest.rest().given().when().get("/list").then().statusCode(200).log().all();
	}
	
	@WithTags(
			{
				@WithTag("studentFeature:NEGATIVE"),
				@WithTag("studentFeature:SMOKE")
			}
			)
	@Title("Provide a 400 status code when invalid resource is accessed")
	@Test
	public void invalidResource() {
		SerenityRest.rest().given().when().get("/list0tdd").then().statusCode(400).log().all();
	}
}
