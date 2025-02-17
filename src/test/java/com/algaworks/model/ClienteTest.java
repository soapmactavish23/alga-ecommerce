package com.algaworks.model;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.mysql.cj.xdevapi.Client;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ClienteTest extends EntityManagerTest {

    @Test
    public void create() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Henrick");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clientSalvo = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(clientSalvo);
    }

    public void update() {

    }

}