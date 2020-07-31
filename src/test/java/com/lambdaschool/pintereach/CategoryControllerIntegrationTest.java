package com.lambdaschool.pintereach;


import io.restassured.http.ContentType;
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
public class CategoryControllerIntegrationTest {
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

    //    GET /categories/categories
    @Test
    public void whenMeasuredReponseTime() {
        given().contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/articles/articles")
                .then()
                .time(lessThan(5000L));
    }


    //    POST /categories/category
    @Test
    public void givenPostACategory() throws Exception {
        String body = "{\"categoryName\": \"Physics\"}";
        given().contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("/categories/category")
                .then()
                .statusCode(201);
    }


    //    GET /categories/category/{categoryid}
    @Test
    public void givenFoundCategoryId() throws
            Exception {
        long aCategory = 10;

        given().contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/categories/category/" + aCategory)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Physics"));
    }


/*
    //    GET /restaurants/restaurant/name/{name}
    @Test
    public void givenFoundArticleName() throws
            Exception {
        String aCategory = "Test Apple";

        given().when()
                .get("/categories/category/name/" + aCategory)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Apple"));
    }


    //    GET /restaurants/restaurants
    @Test
    public void givenFindAllArticles() {
        given().when()
                .get("/categories/categories")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Apple"));
    }


    //    PUT /categories/category/{categoryid}
   /* @Test
    public void givenUpdateAnArticle() throws
            Exception {
        String body = "{\"title\": \"Particle physics in a superconductor\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";


        given().contentType("application/json")
                .body(body)
                .when()
                .put("/categories/category/10")
                .then()
                .statusCode(200);
    }


    //    DELETE /restaurants/restaurant/{restaurantid}
    //    at the end so I can use restaurant 10 in examples!
    /*@Test
    public void givenDeleteAnArticle() {
        long aCategory = 10L;
        given().when()
                .delete("/categories/category/" + aCategory)
                .then()
                .statusCode(200);
    }*/

}