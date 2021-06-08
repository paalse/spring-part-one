package ru.paalse;

import org.hibernate.cfg.Configuration;
import ru.paalse.persist.Contact;
import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Main06 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//        //INSERT for One To Many
//        try {
//            em.getTransaction().begin();
//            User user = new User("User2", "user2@mail.ru", "12332");
//            em.persist(user);
//            List<Contact> contacts = new ArrayList<>();
//            contacts.add(new Contact("home phone", "+7(955)223-34-45", user));
//            contacts.add(new Contact("work phone", "+7(966)223-34-45", user));
//            contacts.add(new Contact("mobile phone", "+7(977)223-34-45", user));
//            contacts.forEach(em::persist);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }

//        // SELECT for One To Many
//        User user = em.find(User.class, 1L);
//        user.getContacts().forEach(System.out::println);
//        em.close();



        // Выборка с параметрами
        List<Contact> contacts = em.createQuery(
                "select c from User u " +
                        "inner join Contact c on u.id=c.user.id " +
                        "where c.type = 'mobile phone'", Contact.class)
                .getResultList();
        contacts.forEach(System.out::println);

        System.out.println();

        // Выборка с параметрами
        List<User> users = em.createQuery(
                "select u from User u " +
                        "inner join Contact c on u.id=c.user.id " +
                        "where c.type = 'mobile phone'", User.class)
                .getResultList();
        users.forEach(System.out::println);

        em.close();
    }
}