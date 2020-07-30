package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Article;
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

class CategoryServiceImplTest {

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


        List<Category> myList = categoryService.findAllByUser();
        for(Category b: myList)
        {
            System.out.println(b.getCategoryid() + " " + b.getCategoryName());
        }
        List<User> userList = userService.findAll();
        for(User u: userList)
        {
            System.out.println(u.getUserId() + " " + u.getClass());
        }


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByUser()
    {
        assertEquals(4,categoryService.findAllByUser().size());
    }

    @Test
    void findByCategoryIdAndUser()
    {
        assertEquals(4,categoryService.findAllByUser().size());
    }

    @Test
    void delete()
    {

        categoryService.delete(26);
        TestCase.assertEquals(4, categoryService.findAllByUser().size());
    }

    @Test
    void save()
    {
        String categoryName = "genre";

        User

        Category c1 = new Category();
        c1.setCategoryid(21);
        String category2Name = "Test This";
        Category c10 = new Category(category2Name, );


        c1.setCategoryid(26);



        c1 = categoryService.save(c1);



        Category addCategory = categoryService.save(c1);
        assertNotNull(addCategory);
        TestCase.assertEquals(categoryName, addCategory.getCategoryName());
    }

    @Test
    void deleteAll() {
    }

 */
}