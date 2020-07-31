package com.lambdaschool.pintereach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.pintereach.PintereachApplication;
import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= PintereachApplication.class)
@WithMockUser(username = "admin")
class UserControllerTest {

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

        List<User> myList = userService.findAll();
        for(User b: myList)
        {
            System.out.println(b.getUserId() + " " + b.getUsername());
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
    void listAllUsers()
    {
        Assert.assertEquals(8,userService.findAll().size());
    }

    @Test
    void getUserById()
    {



        //assertEquals(er, tr);
    }

    @Test
    void getUserByName() {
    }

    @Test
    void getUserLikeName() {
    }

    @Test
    void addNewUser()
    {

        /*String categoryName= "genre";
        String userName = "karlin";
        User u2 = new User(userName);
        Category c1 = new Category(categoryName);
        c1.setCategoryid(1);
        String category2Name = "Test This";
        Category b2 = new Category(category2Name, "9788489367012", 2007, s1);


        u2.setUserId(26);

        assertEquals(er, tr);*/

    }

    @Test
    void updateFullUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById()
    {
        //assertEquals(er, tr);
    }
}