package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id) throws EntityNotFoundException
    {
        return bookrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Book> findBookByNameLike(String name)
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findByBookTitleContainingIgnoreCase(name).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        newBook.setBooktitle(book.getBooktitle());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());

        return bookrepos.save(newBook);
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }

        return bookrepos.save(currentBook);
    }
}
