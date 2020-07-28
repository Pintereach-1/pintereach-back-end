package com.lambdaschool.pintereach;

import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner {
    /**
     * Connects the Article Service to this process
     */
    @Autowired
    ArticleService articleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;


    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
            Exception {


        articleService.deleteAll();
        categoryService.deleteAll();
        userService.deleteAll();


        // admin, data, user
        User u1 = new User("admin",
                "password");

        userService.save(u1);

        // data, user
        User u2 = new User("karina",
                "krodriguez");

        userService.save(u2);

        // user
        User u3 = new User("jorge",
                "jmanzur");

        userService.save(u3);

        User u4 = new User("brendan",
                "bneil");

        userService.save(u4);

        User u5 = new User("violeta",
                "vdimov");

        userService.save(u5);

        User u6 = new User("hung",
                "htruong");

        userService.save(u6);

        User u7 = new User("tim",
                "tmcdonald");

        userService.save(u7);

        User u8 = new User("william",
                "wherman");

        userService.save(u8);

        /************
         * Seed Articles
         ************/

/*
        Category c1 = new Category("Biology", u1);
        Category c2 = new Category("Physics",u2);
        Category c3 = new Category("English",u3);
        Category c4 = new Category("History",u4);
        Category c5 = new Category("Political Science",u7 );

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);

        Article a1 = new Article("https://www.jstor.org/stable/j.ctvbkk2pq.17", "The Future of Public History – What Shall We Teach Prospectively?: Remarks and Considerations\n" +
                "By Charlotte Bühl-Gramer", c4, "Talks about the future of public history");
        a1 = articleService.save(a1);

        Article a2 = new Article("https://www.jstor.org/stable/10.1641/b570903", "Evolutionary Biology and Human Health\n" +
                "By Cheryl Lyn Dybas", c1, "Talks about Evolutionary Biology");
        a2 = articleService.save(a2);

        Article a3 = new Article("https://www.jstor.org/stable/10.21832/j.ctt1xp3wcc.15", "11 Variation or ‘Error’?: Perception of Pronunciation Variation and Implications for Assessment\n" +
                "Stephanie Lindemann", c3, "Talks about variations in the English language");
        a3 = articleService.save(a3);

        Article a4 = new Article("https://www.jstor.org/stable/24917409", "Particle physics in a superconductor\n" +
                "Alexej Pashkin and Alfred Leitenstorfer", c2, "Talks about Particle Physics");
        a4 = articleService.save(a4);

        Article a5 = new Article("https://www.jstor.org/stable/2140885", "The Political Theories of Jean Jacques Rousseau\n" +
                "Wm. A. Dunning", c5, "Talks about the Rousseau's political theories");
        a5 = articleService.save(a5);
*/

    }
}
