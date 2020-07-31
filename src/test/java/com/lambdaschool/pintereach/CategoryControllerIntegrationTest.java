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
public class CategoryControllerIntegrationTest {
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
                .get("/articles/articles")
                .then()
                .time(lessThan(5000L));
    }


    //    POST /restaurants/restaurant
    @Test
    public void givenPostAnArticle() throws Exception {
        String body = "{\"title\": \"Particle physics in a superconductor\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";
        given().contentType("application/json")
                .body(body)
                .when()
                .post("/categories/category")
                .then()
                .statusCode(201);
    }


    //    GET /restaurants/restaurant/{restaurantId}
    @Test
    public void givenFoundArticleId() throws
            Exception {
        long aCategory = 1;

        given().when()
                .get("/categories/category/" + aCategory)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Particle"));
    }


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


    //    PUT /restaurants/restaurant/{restaurantid}
    @Test
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
    @Test
    public void givenDeleteAnArticle() {
        long aCategory = 10L;
        given().when()
                .delete("/categories/category/" + aCategory)
                .then()
                .statusCode(200);
    }

}