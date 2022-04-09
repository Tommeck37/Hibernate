package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.BookDao;

public class BookConverter implements Converter<String, Book> {

    @Override
    public Book convert(String source) {
        return bookDao.findById(Long.parseLong(source));
    }

    @Autowired
    BookDao bookDao;

}
