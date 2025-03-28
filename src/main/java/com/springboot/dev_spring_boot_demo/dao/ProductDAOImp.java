package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {
    private EntityManager em;

    @Autowired
    public ProductDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = em.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Override
    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public void saveProduct(Product product) {
        em.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }
}