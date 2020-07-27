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

    // http://localhost:2019/categories/categories
    @GetMapping(value = "/categories",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request)
    {
        List<Category> myCategories = categoryService.findAll();
        return new ResponseEntity<>(myCategories,
                HttpStatus.OK);
    }

    // http://localhost:2019/books/book/{bookId}
    @GetMapping(value = "/category/{categoryid}",
            produces = {"application/json"})
    public ResponseEntity<?> getCategoryById(HttpServletRequest request,
                                         @PathVariable
                                                 Long categoryid)
    {
        Category s = categoryService.findCategoryById(categoryid);
        return new ResponseEntity<>(s,
                HttpStatus.OK);
    }

    // POST http://localhost:2019/books/book
    @PostMapping(value = "/category", consumes = "application/json")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody Category newCategory) throws
            URISyntaxException
    {
        newCategory.setCategoryid(0);
        newCategory = categoryService.save(newCategory);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{categoryid}")
                .buildAndExpand(newCategory.getCategoryid())
                .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/categories/category/1
    @PutMapping(value = "/category/{categoryid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullBook(
            @Valid
            @RequestBody
                    Category updateCategory,
            @PathVariable
                    long categoryid)
    {
        updateCategory.setCategoryid(categoryid);
        categoryService.save(updateCategory);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/categories/category/1
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id)
    {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
