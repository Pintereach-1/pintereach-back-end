package com.lambdaschool.pintereach.controllers;


import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.services.CategoryService;
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
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    // http://localhost:2019/books/books
    @GetMapping(value = "/books",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request)
    {
        List<Category> myCategories = categoryService.findAll();
        return new ResponseEntity<>(myCategories,
                HttpStatus.OK);
    }

    // http://localhost:2019/books/book/{bookId}
    @GetMapping(value = "/category/{categoryId}",
            produces = {"application/json"})
    public ResponseEntity<?> getCategoryById(HttpServletRequest request,
                                         @PathVariable
                                                 Long categoryId)
    {
        Book s = bookService.findBookById(bookId);
        return new ResponseEntity<>(s,
                HttpStatus.OK);
    }

    // POST http://localhost:2019/books/book
    @PostMapping(value = "/book", consumes = "application/json")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody Book newBook) throws
            URISyntaxException
    {
        newBook.setBookid(0);
        newBook = bookService.save(newBook);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bookid}")
                .buildAndExpand(newBook.getBookid())
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
                    Book updateBook,
            @PathVariable
                    long bookid)
    {
        updateBook.setBookid(bookid);
        bookService.save(updateBook);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/books/book/1
    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
