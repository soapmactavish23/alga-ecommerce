package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Pedido;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);

    }

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);

    }

}
