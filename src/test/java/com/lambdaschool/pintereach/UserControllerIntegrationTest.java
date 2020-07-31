package com.lambdaschool.pintereach;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    //    GET /restaurants/restaurants
    @Test
    public void whenMeasuredReponseTime() {
        given().when()
                .get("/users/users")
                .then()
                .time(lessThan(5000L));
    }


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

}
