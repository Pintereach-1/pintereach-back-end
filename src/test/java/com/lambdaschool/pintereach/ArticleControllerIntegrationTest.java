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
import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleControllerIntegrationTest {
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



    //    POST /articles/category/{categoryid}
    @Test
    public void givenPostAnArticle() throws Exception {
        String body = "{\"title\": \"Particle physics in a superconductor\\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";
        given()
                .contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("/articles/category/10")
                .then()
                .statusCode(201);
    }


    //    GET /articles/articles/{articleid}
    @Test
    public void givenFoundArticleId() throws
            Exception {
        long anArticle = 17;

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/articles/article/" + anArticle)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Particle"));
    }


    //    PUT http://localhost:2019/articles/article/17
    @Test
    public void givenCategoryIdPostArticles() throws
            Exception {
        long articleId = 17 ;
        String body = "{\"title\": \"Particle2 physics in a superconductor\\nBy Alexej Pashkin and Alfred Leitenstorfer\", \"description\": \"Talks about Particle Physics\", \"imageUrl\": \"https://www.jstor.org/stable/24917409\"}";

        given().contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .put("/articles/article/" + articleId)
                .then()
                .statusCode(200);
    }


    //    GET /articles/articles
    @Test
    public void givenFindAllArticlesBelongingToLoggedInUser() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/articles/articles")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("Particle"));
    }




    //    DELETE http://localhost:2019/articles/article/1
    //    at the end so I can use restaurant 10 in examples!
    @Test
    public void zgivenDeleteAnArticle() {
        long anArticleId = 17;
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/articles/article/" + anArticleId)
                .then()
                .statusCode(200);
    }
}


