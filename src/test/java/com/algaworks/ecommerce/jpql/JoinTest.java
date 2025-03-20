package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void fazerJoin() {
        String jpql = "select p from Pedido p join p.pagamento pag";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 3);
    }
}