package pl.coderslab.app.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }
    public Book editBook(Book book) {
        entityManager.merge(book);
        return book;
    }

    public List<Book> finfAll() {
        return entityManager.createQuery("select b from Book b").getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("select b from Book b where b.rating=:rating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    public List<Book> findBookWithPublisher() {
        return entityManager.createQuery("select b from Book  b where b.publisher is not null").getResultList();
    }
    public List<Book> findBookWithPublisherParameter(Publisher publisher) {
        Query query = entityManager.createQuery("select b from Book  b where b.publisher =:publisher");
        query.setParameter("publisher", publisher);
        return query.getResultList();
    }



    public List<Book> findBookWithAuthor(Author author) {
        Query query = entityManager.createQuery("SELECT distinct b FROM Book b join FETCH b.authors " +
                "WHERE :author member of b.authors");
        query.setParameter("author", author);
        return query.getResultList();
    }
    public List<Book> findAll(){
        return entityManager.createQuery("select b from Book b").getResultList();
    }

}
