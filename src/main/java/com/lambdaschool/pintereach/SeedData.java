package com.lambdaschool.pintereach;

import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SeedData
{/**
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
            Exception
    {
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


        Category c1 = new Category("Fiction");
        Category c2 = new Category("Technology");
        Category c3 = new Category("Travel");
        Category c4 = new Category("Business");
        Category c5 = new Category("Religion");

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);

        Article a1 = new Book("Test Flatterland", "9780738206752", 2001, s1);
        b1.getWrotes()
                .add(new Wrote(a6, new Book()));
        b1 = bookService.save(b1);

        Book b2 = new Book("Test Digital Fortess", "9788489367012", 2007, s1);
        b2.getWrotes()
                .add(new Wrote(a2, new Book()));
        b2 = bookService.save(b2);

        Book b3 = new Book("Test The Da Vinci Code", "9780307474278", 2009, s1);
        b3.getWrotes()
                .add(new Wrote(a2, new Book()));
        b3 = bookService.save(b3);

        Book b4 = new Book("Test Essentials of Finance", "1314241651234", 0, s4);
        b4.getWrotes()
                .add(new Wrote(a3, new Book()));
        b4.getWrotes()
                .add(new Wrote(a5, new Book()));
        b4 = bookService.save(b4);

        Book b5 = new Book("Test Calling Texas Home", "1885171382134", 2000, s3);
        b5.getWrotes()
                .add(new Wrote(a4, new Book()));
        b5 = bookService.save(b5);

//        System.out.println("***** BOOK IDs *****");
//        System.out.println(b1.getTitle() + " " + b1.getBookid());
//        System.out.println(b2.getTitle() + " " + b2.getBookid());
//        System.out.println(b3.getTitle() + " " + b3.getBookid());
//        System.out.println(b4.getTitle() + " " + b4.getBookid());
//        System.out.println(b5.getTitle() + " " + b5.getBookid());
//
//        System.out.println();
//        System.out.println("***** Section Ids *****");
//        System.out.println(s1.getName() + " " + s1.getSectionid());
//        System.out.println(s2.getName() + " " + s2.getSectionid());
//        System.out.println(s3.getName() + " " + s3.getSectionid());
//        System.out.println(s4.getName() + " " + s4.getSectionid());
//        System.out.println(s5.getName() + " " + s5.getSectionid());
//
//        System.out.println();
//        System.out.println("***** Author Ids *****");
//        System.out.println(a1.getFname() + " " + a1.getLname() + " " + a1.getAuthorid());
//        System.out.println(a2.getFname() + " " + a2.getLname() + " " + a2.getAuthorid());
//        System.out.println(a3.getFname() + " " + a3.getLname() + " " + a3.getAuthorid());
//        System.out.println(a4.getFname() + " " + a4.getLname() + " " + a4.getAuthorid());
//        System.out.println(a5.getFname() + " " + a5.getLname() + " " + a5.getAuthorid());
//        System.out.println(a6.getFname() + " " + a6.getLname() + " " + a6.getAuthorid());
    }
}
