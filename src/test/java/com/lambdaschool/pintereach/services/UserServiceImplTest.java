package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.PintereachApplication;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PintereachApplication.class)
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

        List<Category> categoryList = categoryService.findAllByUser(userService.findUserById(2));
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
        assertEquals(8,userService.findAll().size());
    }

    @Test
    void delete()
    {
        userService.delete(2);
        TestCase.assertEquals(0, userService.findAll().size());
    }

    @Test
    void findByName()
    {

    }

    @Test
    void save()
    {




        String user2Name = "karina";
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