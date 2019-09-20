package com.lambdaschool.starthere.repository;


import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AuthorsRepository extends CrudRepository<Authors, Long>
{

    @Modifying
    @Query(value = "DELETE FROM bookauthors WHERE authorid = :authorid", nativeQuery = true)
    void deleteAuthorsFrombookauthors(long authorid);

//    @Query(value = "SELECT s.bookid, firstname, lastname FROM bookauthors s INNER JOIN authors c on s.bookid=c.bookid GROUP BY s.bookid, firstname, lastname ", nativeQuery = true);

}

