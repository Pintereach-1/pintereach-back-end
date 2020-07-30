package com.lambdaschool.pintereach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        List<Article> myList = articleService.findAll();
        for(Article b: myList)
        {
            System.out.println(b.getArticleid() + " " + b.getTitle());
        }

        List<Category> categoryList = categoryService.findAllByUser(userService.findUserById(2));
        for(Category c: categoryList)
        {
            System.out.println(c.getCategoryid() + " " + c.getClass());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllArticles()
    {


        assertEquals(er, tr);
    }

    @Test
    void getArticleById()
    {


        assertEquals(er, tr);
    }

    @Test
    void addNewArticle()
    {


        String categoryName = "genre";

        Article a2 = new Article();
        Category s1 = new Category(categoryName);
        s1.setCategoryid(1);
        String article2Name = "Test This";
        Article a2 = new Article(article2Name, "9788489367012", 2007, s1);


        a2.setArticleid(26);



        Category addCategory = categoryService.save(c1);
        assertNotNull(addCategory);
        TestCase.assertEquals(article2Name, addArticle.getCategoryName());
    }

    @Test
    void updateFullArticle() {
    }

    @Test
    void deleteArticleById()
    {
        assertEquals(er, tr);
    }
}