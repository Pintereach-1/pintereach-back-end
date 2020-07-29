package com.lambdaschool.pintereach.controllers;


import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.services.CategoryService;
import com.lambdaschool.pintereach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserService userService;

    // http://localhost:2019/categories/categories
    @GetMapping(value = "/categories",
            produces = {"application/json"})
    public ResponseEntity<?> listAllCategories(HttpServletRequest request)
    {
        List<Category> myCategories = categoryService.findAll();
        return new ResponseEntity<>(myCategories,
                HttpStatus.OK);
    }

    // http://localhost:2019/categories/category/{categoryId}
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

    // POST http://localhost:2019/categories/category
    @PostMapping(value = "/category", consumes = "application/json")
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody Category newCategory) throws
            URISyntaxException
    {
        org.springframework.security.core.userdetails.User userdetails = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userdetails.getUsername();
        User user = userService.findByName(username);

        newCategory.setCategoryid(0);
        newCategory.setUser(user);
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
    public ResponseEntity<?> updateFullCategory(
            @Valid
            @RequestBody
                    Category updateCategory,
            @PathVariable
                    long categoryid)
    {
        org.springframework.security.core.userdetails.User userdetails = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userdetails.getUsername();
        User user = userService.findByName(username);

        updateCategory.setUser(user);
        updateCategory.setCategoryid(categoryid);
        categoryService.save(updateCategory);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/categories/category/1
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteCategoryById(
            @PathVariable
                    long id)
    {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
