package com.marcuseisele.linkshortener.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkControllerIntegrationTest {


    public final static String NON_EXISTING_SHORTLINK = "1234-56789";
    public final static String EXISTING_SHORTLINK = "1337-1337";

    @LocalServerPort
    private int port;

    @Before
    public void setup(){
        RestAssured.port = port;
    }

    @Test
    public void getLink_whenLinkDoesNotExist_shouldReturn404() {
        when().
            get("/links/{id}", NON_EXISTING_SHORTLINK).prettyPeek().
        then().
           statusCode(404);
    }

    @Test
    @Sql("/insertExistingLink.sql")
    public void getLink_whenLinkDoesExist_shouldReturnRedirect() {
        when().
            get("/links/{id}", EXISTING_SHORTLINK).prettyPeek().
        then().
            statusCode(200);

    }

    @Test
    public void createLink_whenLinkIsPosted_shouldCreateAndReturnWith201() {
        given().
            contentType(ContentType.JSON).
            body("{\"target\":\"target\"}").
        when().
            post("/links").prettyPeek().
        then().
            statusCode(201).
            body("target", is("target")).
            body("id", isA(Integer.class)).
            body("shortLink", isA(String.class));
    }
}
