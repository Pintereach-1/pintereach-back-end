package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.PintereachApplication;
import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= PintereachApplication.class)
class ArticleServiceImplTest {

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
        for(Category s: categoryList)
        {
            System.out.println(s.getCategoryid() + " " + s.getClass());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findArticleById()
    {
        Article article = articleService.findArticleById(17);

        User u1 = userService.findByName("karina");
        assertEquals("Particle physics in a superconductor\\n\" +\n" +
                "                \"By Alexej Pashkin and Alfred Leitenstorfer", articleService.findByArticleIdAndUser( article.getArticleid(),u1));
    }

    @Test
    void findByArticleIdAndUser()
    {
        assertEquals(4,articleService.findAll().size());
    }

    @Test
    void findAllByUser()
    {
        assertEquals(4,articleService.findAll().size());
    }

    @Test
    void delete()
    {
        articleService.delete(26);
        TestCase.assertEquals(4, articleService.findAll().size());
    }

    @Test
    void save()
    {
        /*String categoryName = "genre";


        Category c1 = new Category(categoryName, );
        c1.setCategoryid(21);
        String article2Name = "Test This";
        Article a2 = new Article(article2Name, c1);


        a2.setArticleid(26);



        c1 = categoryService.save(c1);
        a2 = categoryService.save(a2);


        Article addArticle = articleService.save(a2);
        assertNotNull(addArticle);
        TestCase.assertEquals(article2Name, addArticle.getTitle());*/
    }

    @Test
    void update() {
    }

    @Test
    void deleteAll() {
    }


}
