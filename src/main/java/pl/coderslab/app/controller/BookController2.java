package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController2 {

    @Autowired
    BookDao bookDao;

    @Autowired
    PublishDao publishDao;

    @Autowired
    AuthorDao authorDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisher = publishDao.addPublisher(publisher);

        Book book = new Book();
        book.setRating(1);
        book.setTitle("Pan Tadeusz");
        book.setDescription("Opis");
        book.setPublisher(publisher);
        bookDao.saveBook(book);

        return "done";
    }

    @RequestMapping("/add2")
    @ResponseBody
    public String addBook2() {
        Publisher publisher = publishDao.findById(1);
        List<Author> authors = new ArrayList<>();
        Book book = new Book();
        book.setRating(1);
        book.setTitle("Pan Wołodyjowski");
        book.setDescription("Opis");
        book.setPublisher(publisher);
        bookDao.saveBook(book);
        Publisher publisher1 = new Publisher();
        publisher1.setName("Moja Książka");
        publishDao.savePublisher(publisher1);
        Author author = authorDao.findById(1);
        Author author1 = authorDao.findById(2);
        authors.add(author);
        authors.add(author1);
        book.setAuthors(authors);

        return "done";
    }


    @RequestMapping("/get")
    @ResponseBody
    public String getBookByID() {
        Book book = bookDao.findById(1);
        Publisher publisher = book.getPublisher();

        return publisher.getName();
    }
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("books", bookDao.findAll());

        return "books-list";
    }
}