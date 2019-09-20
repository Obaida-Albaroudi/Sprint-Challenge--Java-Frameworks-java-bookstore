package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.AuthorsService;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    AuthorsService authorsService;

    @Autowired
    BookService bookService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "ILuvM4th!", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));
        u1 = userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        u2.getUseremails()
          .add(new Useremail(u2, "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "bunny@email.local"));
        u2 = userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User u3 = new User("testbarn", "ILuvM4th!", users);
        u3.getUseremails()
          .add(new Useremail(u3, "barnbarn@email.local"));
        u3 = userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("testcat", "password", users);
        u4 = userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("testdog", "password", users);
        u5 = userService.save(u5);

        System.out.println("\n*** Seed Data ***");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);
        System.out.println("*** Seed Data ***\n");


        Authors John = new Authors(1, "John", "Mitchell");

        Book Flatterland = new Book(1, "Flatterland", "9780738206752", 2001);
        Book DigitalFortess = new Book(2, "Digital Fortess", "9788489367012", 2007);
        Book DaVinci = new Book(3, "The Da Vinci Code", "9780307474278", 2009);
        Book Essentials = new Book(4, "Essentials of Finance", "1314241651234", 0);
        Book Calling = new Book(5, "Calling Texas Home", "1885171382134", 2000);

        bookService.save(Flatterland);
        bookService.save(DigitalFortess);
        bookService.save(DaVinci);
        bookService.save(Essentials);
        bookService.save(Calling);

        System.out.println(Flatterland);
        System.out.println(DigitalFortess);
        System.out.println(DaVinci);
        System.out.println(Essentials);
        System.out.println(Calling);



//        INSERT INTO author (authorid, fname, lname) VALUES (2, 'Dan', 'Brown');
//        INSERT INTO author (authorid, fname, lname) VALUES (3, 'Jerry', 'Poe');
//        INSERT INTO author (authorid, fname, lname) VALUES (4, 'Wells', 'Teague');
//        INSERT INTO author (authorid, fname, lname) VALUES (5, 'George', 'Gallinger');
//        INSERT INTO author (authorid, fname, lname) VALUES (6, 'Ian', 'Stewart');


//        INSERT INTO wrote (bookid, authorid) VALUES (1, 6);
//        INSERT INTO wrote (bookid, authorid) VALUES (2, 2);
//        INSERT INTO wrote (bookid, authorid) VALUES (3, 2);
//        INSERT INTO wrote (bookid, authorid) VALUES (4, 5);
//        INSERT INTO wrote (bookid, authorid) VALUES (4, 3);
//        INSERT INTO wrote (bookid, authorid) VALUES (5, 4);
//        alter sequence hibernate_sequence restart with 25;
    }
}