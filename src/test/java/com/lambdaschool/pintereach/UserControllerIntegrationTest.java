package com.lambdaschool.pintereach;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.lessThan;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    String token;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        token = given().
                param("username", "karina").
                param("password", "krodriguez").
                param("grant_type", "password").
                header("Accept", ContentType.JSON.getAcceptHeader()).
                header("Authorization", "Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0"). // + Base64.getEncoder().encode("lambda-client:lambda-secret".getBytes())).
                post("/login").
                then().
                statusCode(200).
                extract().
                response().
                jsonPath().getString("access_token");
    }

    //    GET /restaurants/restaurants
    @Test
    public void whenMeasuredReponseTime() {
        given().when()
                .get("/users/users")
                .then()
                .time(lessThan(5000L));
    }
/*

    //    POST /restaurants/restaurant
    @Test
    public void givenPostAUser() throws Exception {
        String body = "{\"title\": \"Particle physics in a superconductor\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";
        given().contentType("application/json")
                .body(body)
                .when()
                .post("/users/user")
                .then()
                .statusCode(201);
    }


    //    GET /restaurants/restaurant/{restaurantId}
    @Test
    public void givenFoundUserId() throws
            Exception {
        long aUser = 1;

        given().when()
                .get("/users/user/" + aUser)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Particle"));
    }


    //    GET /restaurants/restaurant/name/{name}
    @Test
    public void givenFoundUserName() throws
            Exception {
        String aUser = "Test Apple";

        given().when()
                .get("/users/user/name/" + aUser)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Apple"));
    }


    //    GET /restaurants/restaurants
    @Test
    public void givenFindAllUsers() {
        given().when()
                .get("/users/users")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Apple"));
    }


    //    PUT /restaurants/restaurant/{restaurantid}
    @Test
    public void givenUpdateAUser() throws
            Exception {
        String body = "{\"title\": \"Particle physics in a superconductor\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";


        given().contentType("application/json")
                .body(body)
                .when()
                .put("/users/user/10")
                .then()
                .statusCode(200);
    }


    //    DELETE /restaurants/restaurant/{restaurantid}
    //    at the end so I can use restaurant 10 in examples!
    @Test
    public void givenDeleteAUser() {
        long aUser = 10L;
        given().when()
                .delete("/users/user/" + aUser)
                .then()
                .statusCode(200);
    }
*/
}
