package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    private BookService bookService;

    // Please note there is no way to add students to course yet!

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllStudents()
    {
        List<Book> myBooks = bookService.findAll();
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }


    @PutMapping(value = "/book/{bookid}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long bookid)
    {
        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/book/{bookid}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
