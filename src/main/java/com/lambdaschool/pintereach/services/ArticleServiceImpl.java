package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.exception.ResourceNotFoundException;
import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value="articleService")
public class ArticleServiceImpl
        implements ArticleService {
    @Autowired
    UserAuditing userAuditing;

    @Autowired
    ArticleRepository articlerepos;

    @Autowired
    CategoryService categoryService;



    @Override
    public List<Article> findAll() {
        List<Article> list = new ArrayList<>();
        articlerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Article findArticleById(long id) {
        return articlerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " Not Found!"));
    }

    @Override
    public Article findByArticleIdAndUser(Long articleId, User user)
    {
        return articlerepos.findByArticleidAndUser(articleId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + articleId + " Not Found!"));
    }

    @Override
    public List<Article> findAllByUser(User user)
    {
        return articlerepos.findAllByUser(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (articlerepos.findById(id)
                .isPresent()) {
            articlerepos.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Article save(Article article) {
        /*
        Article newArticle = new Article();

        //can try to figure this block of code later
        if (article.getArticleid() != 0) {
            articlerepos.findById(article.getArticleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Article id " + article.getArticleid() + " not found!"));
        }

        newArticle.setTitle(article.getTitle());


        if (article.getCategory() != null)
        {
            newArticle.setCategory(categoryService.findCategoryById(article.getCategory()
                    .getCategoryid()));
        }

*/
        return articlerepos.save(article);
    }

    @Transactional
    @Override
    public Article update(Article article,
                       long id) {
        Article currentArticle = findArticleById(id);

        /*if (article.getTitle() != null) {
            currentArticle.setTitle(article.getTitle());
        }


        if (article.getCategory() != null) {
            currentArticle.setCategory(categoryService.findByCategoryIdAndUserId(article.getCategory()
                    .getCategoryid()));
        }*/



        return articlerepos.save(currentArticle);
    }

    @Transactional
    @Override
    public void deleteAll() {
        articlerepos.deleteAll();
    }
}
