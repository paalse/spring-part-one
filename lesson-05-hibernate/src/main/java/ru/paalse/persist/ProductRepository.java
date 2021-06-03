package ru.paalse.persist;


import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    /**
     * Получение всего списка продуктов
     *
     * @return
     */
    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("from Product", Product.class)
                .getResultList();
        em.close();
        return products;
    }

    /**
     * Удаление продукта по ID
     *
     * @param id
     */
    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }
        em.close();
    }

    /**
     * Сохранение нового либо изменение имеющегося продукта
     *
     * @param product
     * @return
     */
    public void saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }
}
