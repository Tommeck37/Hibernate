package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.PublishDao;
import pl.coderslab.app.entity.Publisher;

@Controller
public class PubisherController {
    public final PublishDao publishDao;

    @Autowired
    public PubisherController(PublishDao publishDao) {
        this.publishDao = publishDao;
    }
    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher's name");
        publishDao.savePublisher(publisher);
        return "Id dodanego publishera to:"
                + publisher.getId().toString();

    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Publisher publisher = publishDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Publisher publisher = publishDao.findById(id);
        publishDao.delete(publisher);
        return "deleted";
    }
}
