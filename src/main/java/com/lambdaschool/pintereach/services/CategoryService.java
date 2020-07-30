package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;

import java.util.List;

public interface CategoryService
{
    List<Category> findAllByUser(User user);

    Category findByCategoryIdAndUser(Long categoryId, User user);

    void delete(long id);

    Category save(Category category);

    //Category update(Category category, long id);

    void deleteAll();
}
