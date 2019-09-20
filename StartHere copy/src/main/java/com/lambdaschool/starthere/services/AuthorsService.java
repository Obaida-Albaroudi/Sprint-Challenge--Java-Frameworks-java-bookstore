package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;


import java.util.ArrayList;

public interface AuthorsService
{
    ArrayList<Authors> findAll();

    void delete(long id);

    Authors findAuthorsById(long id);

    Authors save(Authors authors);

    Authors update(Authors authors, long id);

}
