package com.lambdaschool.pintereach.repositories;

import com.lambdaschool.pintereach.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository
    extends CrudRepository<Article,Long>
{

}
