package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.util.List;

public interface BookService
{
    List<Book> findAll();

    Book findBookById(long id);

    List<Book> findBookByNameLike(String name);

    void delete(long id);

    Book save(Book book);

    Book update(Book book, long id);
}
