package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.User;

import java.util.List;

public interface ArticleService
{
    List<Article> findAll();

    Article findArticleById(long id);

    Article findByArticleIdAndUser(Long articleId, User user);

    List<Article> findAllByUser(User user);

    void delete(long id);

    Article save(Article article);

    Article update(Article article,
                long id);

    void deleteAll();
}
