package com.lambdaschool.pintereach.services;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl
{
    @Autowired
    UserAuditing userAuditing;

    @Autowired
    BookRepository bookrepos;

    @Autowired
    SectionService sectionService;

    @Autowired
    AuthorRepository authorrepos;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " Not Found!"));
    }


    @Transactional
    @Override
    public void delete(long id)
    {
        if (bookrepos.findById(id)
                .isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Book with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        if (book.getBookid() != 0)
        {
            bookrepos.findById(book.getBookid())
                    .orElseThrow(() -> new ResourceNotFoundException("Book id " + book.getBookid() + " not found!"));
        }

        newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setCopy(book.getCopy());
        if (book.getSection() != null)
        {
            newBook.setSection(sectionService.findSectionById(book.getSection()
                    .getSectionid()));
        }

        newBook.getWrotes()
                .clear();
        for (Wrote w : book.getWrotes())
        {
            Author addAuthor = authorrepos.findById(w.getAuthor()
                    .getAuthorid())
                    .orElseThrow(() -> new ResourceNotFoundException("Author Id " + w.getAuthor()
                            .getAuthorid() + " Not Found!"));
            newBook.getWrotes()
                    .add(new Wrote(addAuthor, newBook));
        }
        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public Book update(Book book,
                       long id)
    {
        Book currentBook = findBookById(id);

        if (book.getTitle() != null)
        {
            currentBook.setTitle(book.getTitle());
        }

        if (book.getIsbn() != null)
        {
            currentBook.setIsbn(book.getIsbn());
        }

        if (book.hasvalueforcopy)
        {
            currentBook.setCopy(book.getCopy());
        }

        if (book.getSection() != null)
        {
            currentBook.setSection(sectionService.findSectionById(book.getSection()
                    .getSectionid()));
        }

        if (book.getWrotes()
                .size() > 0)
        {
            currentBook.getWrotes()
                    .clear();
            for (Wrote w : book.getWrotes())
            {
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
    public void deleteAll()
    {
        bookrepos.deleteAll();
    }
}
