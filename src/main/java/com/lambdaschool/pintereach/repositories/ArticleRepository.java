package com.lambdaschool.pintereach.repositories;

import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.Category;
import com.lambdaschool.pintereach.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository
    extends CrudRepository<Article,Long>
{
    @Query("SELECT a FROM Article a LEFT JOIN a.category c " +
            "WHERE a.articleid = :articleid AND c.user = :user")
    Optional<Article> findByArticleidAndUser(Long articleid, User user);


    @Query("SELECT a FROM Article a LEFT JOIN a.category c " +
            "WHERE c.user = :user")
    List<Article> findAllByUser(User user);


}
