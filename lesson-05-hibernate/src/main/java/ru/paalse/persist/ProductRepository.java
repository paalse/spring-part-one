package ru.paalse.persist;

import javax.persistence.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {

    private EntityManagerFactory emFactory = null;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    /**
     * Поиск продукта по ID
     *
     * @param id
     * @return
     */
    public Product findById(Long id) {
        return executeForEntityManager(em -> em.find(Product.class, id));
    }

    /**
     * Получение всего списка продуктов
     *
     * @return
     */
    public List<Product> findAll() {
        return executeForEntityManager(em -> em.createQuery("from Product", Product.class).getResultList());
    }

    /**
     * Удаление продукта по ID
     *
     * @param id
     */
    public void deleteById(Long id) {
        executeInTransaction(em -> {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        });
    }

    /**
     * Сохранение нового либо изменение имеющегося продукта
     *
     * @param product
     * @return
     */
    public void saveOrUpdate(Product product) {
        executeForEntityManager(em -> em.merge(product));
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