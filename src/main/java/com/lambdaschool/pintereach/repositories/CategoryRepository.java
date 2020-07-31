package com.lambdaschool.pintereach.repositories;

import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.List;

public interface CategoryRepository
    extends CrudRepository<Category, Long>
{
    Optional<Category> findByCategoryidAndUser(Long categoryId, User user);
    List<Category> findAllByUser(User user);

}
