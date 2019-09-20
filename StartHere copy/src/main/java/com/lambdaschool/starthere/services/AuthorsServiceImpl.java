package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService
{
    @Autowired
    private AuthorsRepository authorsrepos;

    @Override
    public ArrayList<Authors> findAll()
    {
        ArrayList<Authors> list = new ArrayList<>();
        authorsrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (authorsrepos.findById(id).isPresent())
        {
            authorsrepos.deleteAuthorsFrombookauthors(id);
            authorsrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Authors findAuthorsById(long id) throws EntityNotFoundException
    {
        return authorsrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Transactional
    @Override
    public Authors save(Authors authors)
    {
        Authors newAuthors = new Authors();

        newAuthors.setFirstname(authors.getFirstname());
        newAuthors.setLastname(authors.getLastname());

        return authorsrepos.save(newAuthors);
    }
    @Transactional
    @Override
    public Authors update(Authors authors, long id)
    {
        Authors currentAuthors = authorsrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (authors.getFirstname() != null)
        {
            currentAuthors.setFirstname(currentAuthors.getFirstname());
        }

        if (authors.getLastname() != null)
        {
            currentAuthors.setLastname(currentAuthors.getLastname());
        }

        return authorsrepos.save(currentAuthors);
    }
}
