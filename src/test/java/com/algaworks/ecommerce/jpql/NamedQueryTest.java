package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NamedQueryTest extends EntityManagerTest {

    @Test
    public void executarConsulta() {
        TypedQuery<Produto> typedQuery = entityManager
                .createNamedQuery("Produto.listarPorCategoria", Produto.class);
        typedQuery.setParameter("categoria", 2);

        List<Produto> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}