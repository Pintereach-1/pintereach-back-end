package com.lambdaschool.pintereach.repositories;

import com.lambdaschool.pintereach.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository
    extends CrudRepository<Category, Long>
{
}
