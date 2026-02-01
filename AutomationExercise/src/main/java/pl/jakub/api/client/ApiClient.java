package pl.jakub.api.client;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {
	public RequestSpecification request() {
		return RestAssured.given().baseUri("https://automationexercise.com").basePath("/api");
	}
}
