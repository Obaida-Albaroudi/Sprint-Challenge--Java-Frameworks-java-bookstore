package com.lambdaschool.starthere.repository;


import com.lambdaschool.starthere.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>
{
    List<Book> findBybooktitleContainingIgnoreCase(String name);
}
