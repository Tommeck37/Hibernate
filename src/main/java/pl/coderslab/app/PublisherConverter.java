package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.entity.PublishDao;
import pl.coderslab.app.entity.Publisher;

public class PublisherConverter implements Converter<String, Publisher> {
    @Autowired
    PublishDao publishDao;

    @Override
    public Publisher convert(String source) {
        return publishDao.findById(Long.parseLong(source));
    }
}
