package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike() {
        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("nome", "a");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

}
