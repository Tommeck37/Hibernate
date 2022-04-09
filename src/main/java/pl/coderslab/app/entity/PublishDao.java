package pl.coderslab.app.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublishDao {
    @PersistenceContext
    EntityManager entityManager;
    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public  void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher));
    }
    public Publisher addPublisher(Publisher publisher){
        entityManager.persist(publisher);
        return publisher;
    }
    public List<Publisher> getAll() {
        return entityManager.createQuery("select p from Publisher p").getResultList();
    }

}
