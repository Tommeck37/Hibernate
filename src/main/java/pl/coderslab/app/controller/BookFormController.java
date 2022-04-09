package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.BookDao;
import pl.coderslab.app.entity.PublishDao;
import pl.coderslab.app.entity.Publisher;

import java.util.List;

@Controller
public class BookFormController {
    @Autowired
    BookDao bookDao;

    @Autowired
    PublishDao publishDao;

    @RequestMapping("/add-book")
    public String showBookForm(Model model){

        model.addAttribute("book", new Book());

        return "add-book-form";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String saveBookForm(Book book){

        bookDao.saveBook(book);

        return "book-list";
    }

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers(){
        return publishDao.getAll();
    }

    @RequestMapping("/removal/{id}")
    public String deleteBook(@PathVariable Long id){
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "redirect:/list";
    }
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        Book book = bookDao.findById(id);
        model.addAttribute("book", book);
        return "edit-form";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String saveEditBook(@PathVariable Long id, Book book) {
//        book = bookDao.findById(id);
        bookDao.editBook(book);

        return "redirect:/list";
    }
}
