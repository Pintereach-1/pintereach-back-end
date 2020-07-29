package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.exception.ResourceFoundException;
import com.lambdaschool.pintereach.exception.ResourceNotFoundException;
import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.repositories.ArticleRepository;
import com.lambdaschool.pintereach.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value="categoryService")
public class CategoryServiceImpl
        implements CategoryService
{
    @Autowired
    UserAuditing userAuditing;

    @Autowired
    CategoryRepository categoryrepos;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;





    @Override
    public List<Category> findAll()
    {
        List<Category> list = new ArrayList<>();
        categoryrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Category findCategoryById(long id)
    {
        return categoryrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " Not Found!"));
    }


    @Transactional
    @Override
    public void delete(long id)
    {
        if (categoryrepos.findById(id)
                .isPresent())
        {
            categoryrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Book with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Category save(Category category)
    {
    /*
        Category newCategory = new Category();
        if (category.getCategoryName() != null)
        {
            newCategory.setCategoryName(category.getCategoryName());
        }


        newCategory.setUser(category.getUser());
    */
        return categoryrepos.save(category);
    }

    @Transactional
    @Override
    public Category update(Category category,
                       long id)
    {
        Category currentCategory = findCategoryById(id);
        if (category.getCategoryName() !=null)
        {
            throw new ResourceFoundException("Category not updated.");
        }

        if (category.getCategoryName() != null)
        {
            currentCategory.setCategoryName(category.getCategoryName());
        }

        if (category.getUser() != null)
        {
            currentCategory.setUser(category.getUser());
        }
        return categoryrepos.save(currentCategory);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        categoryrepos.deleteAll();
    }
}
