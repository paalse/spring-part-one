package ru.paalse.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserRepository {

    private EntityManagerFactory emFactory = null;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    /**
     * Поиск пользователя по ID
     *
     * @param id
     * @return
     */
    public User findById(Long id) {
        return executeForEntityManager(em -> em.find(User.class, id));
    }

    /**
     * Получение всего списка пользователей
     *
     * @return
     */
    public List<User> findAll() {
        return executeForEntityManager(em -> em.createQuery("from User", User.class).getResultList());
    }

    /**
     * Удаление пользователя по ID
     *
     * @param id
     */
    public void deleteById(Long id) {
        executeInTransaction(em -> {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
        });
    }

    /**
     * Сохранение нового либо изменение имеющегося пользователя
     *
     * @param user
     * @return
     */
    public void saveOrUpdate(User user) {
        executeForEntityManager(em -> em.merge(user));
    }

    /**
     * Создание EntityManager и выподнения обращения к БД без транзакции
     *
     * @param function
     * @param <R>
     * @return
     */
    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Создание EntityManager и выподнения обращения с транзакцией
     *
     * @param consumer
     */
    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}