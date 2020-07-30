package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;



class UserServiceImplTest {

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

        List<Category> categoryList = categoryService.findAllByUser();
        for(Category b: categoryList)
        {
            System.out.println(b.getCategoryid() + " " + b.getCategoryName());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findUserById()
    {
        assertEquals("Test Essentials of Finance", userService.findUserById(29).getUsername());
    }

    @Test
    void findByNameContaining() {
    }

    @Test
    void findAll()
    {
        assertEquals(4,userService.findAll().size());
    }

    @Test
    void delete()
    {
        userService.delete(26);
        TestCase.assertEquals(4, userService.findAll().size());
    }

    @Test
    void findByName()
    {

    }

    @Test
    void save()
    {
        String sectionName = "genre";



        String user2Name = "Test This";
        User u2 = new User(user2Name, "here");


        u2.setUserId(26);




        u2 = userService.save(u2);


        User addUser = userService.save(u2);
        assertNotNull(addUser);
        TestCase.assertEquals(user2Name, addUser.getUsername());
    }

    @Test
    void update() {
    }

    @Test
    void deleteAll() {
    }
}