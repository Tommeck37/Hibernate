package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.*;

import java.util.List;

@Controller
public class BookController {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Autowired
    PublishDao publishDao;

    @Autowired
    public BookController(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }


    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Book book = new Book();
        book.setRating(100);
        book.setDescription("Bruce Eckel");
        bookDao.saveBook(book);
        return "Id dodanej książki to:"
                + book.getId().toString();

    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }

    @RequestMapping("/author/add")
    @ResponseBody
    public String helloAuthor() {
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        authorDao.saveAuthor(author);
        return "Id dodanego autora to: "
                + author.getId();

    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.getFirstName().toString() + " " + author.getLastName();
    }

    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted autor number " + id;
    }

    @RequestMapping("/getBookById")
    @ResponseBody
    public String getBookByRating() {
        List<Book> allByRating = bookDao.findAllByRating(1);
        allByRating.forEach((book -> System.out.println(book.getTitle())));
        return allByRating.toString();
    }
    @RequestMapping("/getPublisher/{id}")
    @ResponseBody
    public String findAllBooksByPublisher(@PathVariable String id){
        List<Book> allBooksBYPublisher = bookDao.findBookWithPublisherParameter(publishDao.findById(Long.parseLong(id)));
        allBooksBYPublisher.forEach(book -> System.out.println(book.getTitle()));
        return "books with selected publisher: " + publishDao.findById(Long.parseLong(id)).getName();
    }

    @RequestMapping("/getAuthor/{id}")
    @ResponseBody
    public String findBookWithAuthor(@PathVariable String id){
        List<Book> allBooksBYAuthor = bookDao.findBookWithAuthor(authorDao.findById(Long.parseLong(id)));
        allBooksBYAuthor.forEach(book -> System.out.println(book.getTitle()));
        return "books with selected author: " + authorDao.findById(Long.parseLong(id)).getFirstName() + " " + authorDao.findById(Long.parseLong(id)).getLastName();
    }
}

