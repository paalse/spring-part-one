package ru.paalse;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.paalse.persist.Product;
import ru.paalse.persist.ProductRepository;
import ru.paalse.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class Main05 {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT
//        em.getTransaction().begin(); // Открытие транзакции
//        User user = new User("User1", "qwwq@mail.ru", "123");
//        User user1 = new User("User2", "qwwq@wer.ru", "123");
//        User user2 = new User("User3", "qwwq@xcbcbxd.ru", "123");
//        em.persist(user); // Добавление нового пользователя в БД
//        em.persist(user1); // Добавление нового пользователя в БД
//        em.persist(user2); // Добавление нового пользователя в БД
//        em.getTransaction().commit();   // Фиксация изменений
//        em.close();     // Закрытие транзакции

        // SELECT по ID
//        User user = em.find(User.class, 1L);
//        System.out.println(user);

        // SELECT всех пользователей HQL, JPQL
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);


//        // SELECT по имени HQL, JPQL
//        Object user3 = em.createQuery("from User u where u.username =:username")
//                .setParameter("username", "User3")
//                .getSingleResult();
//        System.out.println(user3);
//
//        // Любой SQL запрос, работаем не с сущностями а с таблицами
//        List<User> userList = em.createNativeQuery("select * from users", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        // Именованный запрос заданный в классе сущности
//        Object user2 = em.createNamedQuery("userByName")
//                .setParameter("username", "User2")
//                .getSingleResult();
//        System.out.println(user2);
//
//        em.close();

        // UPDATE
//        User user = em.find(User.class, 1L);
//
//        em.getTransaction().begin();
//        user.setPassword("4564");
//        em.getTransaction().commit();
//
//
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        em.close();

        //DELETE
//        User user = em.find(User.class, 3L);
//
//        if (user != null) {
//            em.getTransaction().begin();
//            em.remove(user);
//            em.getTransaction().commit();
//        }
//                List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        em.close();

        //DELETE
//
//        em.getTransaction().begin();
//        em.createQuery("delete from User u where u.username=:username")
//                .setParameter("username", "User1")
//                .executeUpdate();
//        em.getTransaction().commit();
//
//       em.close();

        ProductRepository productRepository = new ProductRepository(emFactory);
//
        Product product4 = new Product("Product4", "Описание продукта 4", new BigDecimal(10.00));
        System.out.println(product4);
        Product product5= new Product("Product5", "Описание продукта 5", new BigDecimal(20));
        System.out.println(product5);
        Product product6 = new Product("Product6", "Описание продукта 6", new BigDecimal(30));
        System.out.println(product6);
        productRepository.saveOrUpdate(product4);
        productRepository.saveOrUpdate(product5);
        productRepository.saveOrUpdate(product6);



//        Product product = em.find(Product.class, 1L);
//        product.setPrice(55);
//        productRepository.saveOrUpdate(product);

        System.out.println(productRepository.findAll());

        em.close();
    }
}