package com.lambdaschool.pintereach.controllers;


import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.ArticleService;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController
{
    @Autowired
    private UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    // http://localhost:2019/articles/articles
    @GetMapping(value = "/articles",
            produces = {"application/json"})
    public ResponseEntity<?> listAllArticles(HttpServletRequest request, Principal principal)
    {
        User user = userService.findByName(principal.getName());
        List<Article> myArticles = articleService.findAllByUser(user);
        return new ResponseEntity<>(myArticles,
                HttpStatus.OK);
    }

    // http://localhost:2019/articles/article/{articleid}
    @GetMapping(value = "/article/{articleid}",
            produces = {"application/json"})
    public ResponseEntity<?> getArticleById(HttpServletRequest request,
                                         @PathVariable
                                                 Long articleid, Principal principal)
    {
        User user = userService.findByName(principal.getName());
        Article article = articleService.findByArticleIdAndUser(articleid, user);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    // POST http://localhost:2019/articles/category/9
    @PostMapping(value = "/category/{categoryid}", consumes = "application/json")
    public ResponseEntity<?> addNewArticle(@Valid @RequestBody Article newArticle, @PathVariable Long categoryid, Principal principal) throws
            URISyntaxException
    {
        //newArticle.setArticleid(0);

        User user = userService.findByName(principal.getName());
        Category category = categoryService.findByCategoryIdAndUser(categoryid, user);
        newArticle.setArticleid(0);
        category.setUser(user);
        newArticle = articleService.save(newArticle);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/articles/article/{articleid}")
                .buildAndExpand(newArticle.getArticleid())
                .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/articles/article/19
    @PutMapping(value = "/article/{articleid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullArticle(
            @Valid
            @RequestBody
                    Article updateArticle,
            @PathVariable
                    long articleid, Principal principal)
    {

        User user = userService.findByName(principal.getName());
        Article article = articleService.findByArticleIdAndUser(articleid, user);
        updateArticle.setCategory(article.getCategory());
        articleService.save(updateArticle);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/articles/article/1
    @DeleteMapping(value = "/article/{id}")
    public ResponseEntity<?> deleteArticleById(
            @PathVariable
                    long articleid, Principal principal)
    {
        User user = userService.findByName(principal.getName());
        Article article = articleService.findByArticleIdAndUser(articleid, user);
        articleService.delete(article.getArticleid());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
