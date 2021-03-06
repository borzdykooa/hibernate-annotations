package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainee;
import com.borzdykooa.util.SessionFactoryManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/*
Класс, содержащий методы работы с таблицей trainee (реализация с помощью HQL и SQL)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TraineeDao {

    private static final TraineeDao INSTANCE = new TraineeDao();

    private static final Logger log = Logger.getLogger(TraineeDao.class);

    private static final SessionFactory SESSION_FACTORY = SessionFactoryManager.getSessionFactory();

    public Long save(Trainee trainee) {
        log.info("Method save is called in TraineeDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(trainee);
            session.getTransaction().commit();
            if (id != null) {
                log.info(trainee.toString() + " has been saved successfully!");
            }
            return (Long) id;
        }
    }

    public Trainee find(Long id) {
        log.info("Method find is called in TraineeDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            Trainee trainee = session.find(Trainee.class, id);
            if (trainee != null) {
                log.info(trainee.toString() + " has been found successfully!");
            }
            return trainee;
        }
    }

    public List<Trainee> findAll() {
        log.info("Method findAll is called in TraineeDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Trainee> list = session.createNativeQuery("SELECT * FROM trainee", Trainee.class).list();
            if (list.size() > 0) {
                log.info("List of Trainees: " + list.toString());
            } else {
                log.info("List of Trainees is empty");
            }
            return list;
        }
    }

    public void update(Trainee trainee) {
        log.info("Method update is called in TraineeDao for " + trainee.toString());
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.update(trainee);
            session.getTransaction().commit();
            log.info(trainee.toString() + "' has been updated successfully!");
        }
    }

    public void delete(Trainee trainee) {
        log.info("Method delete is called in TraineeDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.delete(trainee);
            session.getTransaction().commit();
            log.info(trainee.toString() + "' has been deleted successfully!");
        }
    }

    public List<Trainee> findByTrainerName(String name) {
        log.info("Method findByTrainerName is called in TraineeDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Trainee> list = session.createQuery("select t from Trainee t join fetch t.trainer tr where tr.name like :name", Trainee.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
            if (list.size() > 0) {
                log.info("List of Trainees: " + list.toString());
            }
            return list;
        }
    }

    public static TraineeDao getInstance() {
        return INSTANCE;
    }
}
