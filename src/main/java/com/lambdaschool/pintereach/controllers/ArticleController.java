package com.lambdaschool.pintereach.controllers;


import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.services.ArticleService;
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
import java.util.List;

@RestController
public class ArticleController
{
    @Autowired
    ArticleService articleService;

    // http://localhost:2019/articles/articles
    @GetMapping(value = "/articles",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request)
    {
        List<Article> myBooks = articleService.findAll();
        return new ResponseEntity<>(myBooks,
                HttpStatus.OK);
    }

    // http://localhost:2019/articles/article/{articleid}
    @GetMapping(value = "/article/{articleid}",
            produces = {"application/json"})
    public ResponseEntity<?> getBookById(HttpServletRequest request,
                                         @PathVariable
                                                 Long articleid)
    {
        Article s = articleService.findBookById(articleid);
        return new ResponseEntity<>(s,
                HttpStatus.OK);
    }

    // POST http://localhost:2019/books/book
    @PostMapping(value = "/book", consumes = "application/json")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody Article newBook) throws
            URISyntaxException
    {
        newBook.setArticleid(0);
        newBook = articleService.save(newBook);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bookid}")
                .buildAndExpand(newBook.getArticleid())
                .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/books/book/1
    @PutMapping(value = "/book/{bookid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullBook(
            @Valid
            @RequestBody
                    Article updateBook,
            @PathVariable
                    long bookid)
    {
        updateBook.setArticleid(bookid);
        articleService.save(updateBook);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/books/book/1
    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id)
    {
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
