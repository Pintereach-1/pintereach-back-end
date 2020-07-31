package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.exception.ResourceNotFoundException;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<Category> findAllByUser(User user)
    {
        return categoryrepos.findAllByUser(user);
    }

    @Override
    public Category findByCategoryIdAndUser(Long categoryId, User user)
    {
        return categoryrepos.findByCategoryidAndUser(categoryId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + categoryId + " Not Found!"));
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
            throw new ResourceNotFoundException("Category with id " + id + " Not Found!");
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
                       long id, User user)
    {

        Category currentCategory = findByCategoryIdAndUser(id, user);
        currentCategory.setCategoryName(category.getCategoryName());


        return categoryrepos.save(currentCategory);

    }

    @Transactional
    @Override
    public void deleteAll()
    {
        categoryrepos.deleteAll();
    }
}
