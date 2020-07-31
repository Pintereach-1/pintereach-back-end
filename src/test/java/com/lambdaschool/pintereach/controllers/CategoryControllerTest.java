package com.lambdaschool.pintereach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.pintereach.PintereachApplication;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
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
class CategoryControllerTest {
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

        List<Category> myList = categoryService.findAllByUser(userService.findUserById(2));
        for(Category b: myList)
        {
            System.out.println(b.getCategoryid() + " " + b.getCategoryName());
        }

        List<User> sectionList = userService.findAll();
        for(User u: sectionList)
        {
            System.out.println(u.getUserId() + " " + u.getClass());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllCategories()
    {


        //assertEquals(er, tr);
    }

    @Test
    void getCategoryById()
    {


        //assertEquals(er, tr);
    }

    @Test
    void addNewCategory()
    {
        /*String apiUrl="/books/book";

        String userName = "genre";

        Category c2 = new Category();
        User u1 = new User(userName);
        u1.setUserId(1);
        String category2Name = "Test This";
        Category c2 = new Category(category2Name, "9788489367012", 2007, s1);


        c2.setCategoryid(26);

        assertEquals(er, tr);*/
    }

    @Test
    void updateFullCategory() {
    }

    @Test
    void deleteCategoryById()
    {
        //assertEquals(er, tr);
    }
}