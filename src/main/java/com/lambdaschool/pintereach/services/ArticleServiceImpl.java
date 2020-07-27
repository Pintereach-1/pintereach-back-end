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
        if (article.getArticleId() != 0) {
            articlerepos.findById(article.getArticleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book id " + article.getArticleId() + " not found!"));
        }

        newArticle.setTitle(article.getTitle());


        if (article.getCategoryId() == null) {
            newArticle.setCategoryId(categoryService.findCategoryById(article.getCategoryId()));
        }


        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public Book update(Book book,
                       long id) {
        Book currentBook = findBookById(id);

        if (book.getTitle() != null) {
            currentBook.setTitle(book.getTitle());
        }

        if (book.getIsbn() != null) {
            currentBook.setIsbn(book.getIsbn());
        }

        if (book.hasvalueforcopy) {
            currentBook.setCopy(book.getCopy());
        }

        if (book.getSection() != null) {
            currentBook.setSection(sectionService.findSectionById(book.getSection()
                    .getSectionid()));
        }

        if (book.getWrotes()
                .size() > 0) {
            currentBook.getWrotes()
                    .clear();
            for (Wrote w : book.getWrotes()) {
                Author addAuthor = authorrepos.findById(w.getAuthor()
                        .getAuthorid())
                        .orElseThrow(() -> new ResourceNotFoundException("Author Id " + w.getAuthor()
                                .getAuthorid() + " Not Found!"));
                currentBook.getWrotes()
                        .add(new Wrote(addAuthor, currentBook));
            }
        }

        return bookrepos.save(currentBook);
    }

    @Transactional
    @Override
    public void deleteAll() {
        bookrepos.deleteAll();
    }
}
