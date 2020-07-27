package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> findAll();

    Category findCategoryById(long id);

    void delete(long id);

    Category save(Category category);

    Category update(Category category,
                long id);

    void deleteAll();
}
