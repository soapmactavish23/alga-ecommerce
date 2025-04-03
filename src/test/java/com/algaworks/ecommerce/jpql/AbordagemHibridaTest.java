package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class AbordagemHibridaTest extends EntityManagerTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
        EntityManager em = entityManagerFactory.createEntityManager();

        String jpql = "select c from Categoria c";
        TypedQuery<Categoria> typedQuery = em.createQuery(jpql, Categoria.class);

        entityManagerFactory.addNamedQuery("Categoria.listar", typedQuery);
    }

    @Test
    public void usarAbardagemHibrida() {
        TypedQuery<Categoria> typedQuery = entityManager.createNamedQuery("Categoria.listar", Categoria.class);

        List<Categoria> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

}
