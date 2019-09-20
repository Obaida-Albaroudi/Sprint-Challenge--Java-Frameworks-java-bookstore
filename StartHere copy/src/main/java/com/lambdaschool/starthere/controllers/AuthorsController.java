package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/authors")
public class AuthorsController
{
    @Autowired
    private AuthorsService authorsService;

    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses()
    {
        ArrayList<Authors> myAuthors = authorsService.findAll();
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

    @GetMapping(value = "/authors/{authorsid}",
            produces = {"application/json"})
    public ResponseEntity<?> getAuthorsById(
            @PathVariable
                    Long authorsid)
    {
        Authors r = authorsService.findAuthorsById(authorsid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("/authors/{authorsid}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable long authorsid)
    {
        authorsService.delete(authorsid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/authors/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCourse(@Valid
                                              @RequestBody
                                                      Authors newAuthors) throws URISyntaxException
    {
        newAuthors = authorsService.save(newAuthors);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseid}").buildAndExpand(newAuthors.getAuthorid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
