package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.models.Article;
import com.lambdaschool.pintereach.repositories.ArticleRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("articleService")
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
    public Article findBookById(long id) {
        return articlerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " Not Found!"));
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
        Article newArticle = new Article();

        //can try to figure this block of code later
        if (article.getArticleid() != 0) {
            articlerepos.findById(article.getArticleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Book id " + article.getArticleid() + " not found!"));
        }

        newArticle.setTitle(article.getTitle());


        if (article.getCategory() != null)
        {
            newArticle.setCategory(categoryService.findCategoryById(article.getCategory()
                    .getCategoryid()));
        }


        return articlerepos.save(newArticle);
    }

    @Transactional
    @Override
    public Article update(Article book,
                       long id) {
        Article currentBook = findBookById(id);

        if (book.getTitle() != null) {
            currentBook.setTitle(book.getTitle());
        }


        if (book.getCategory() != null) {
            currentBook.setCategory(categoryService.findCategoryById(book.getCategory()
                    .getCategoryid()));
        }



        return articlerepos.save(currentBook);
    }

    @Transactional
    @Override
    public void deleteAll() {
        articlerepos.deleteAll();
    }
}
