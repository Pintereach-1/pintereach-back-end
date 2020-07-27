package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Article;

import java.util.List;

public interface ArticleService
{
    List<Article> findAll();

    Article findBookById(long id);

    void delete(long id);

    Article save(Article article);

    Article update(Article article,
                long id);

    void deleteAll();
}
