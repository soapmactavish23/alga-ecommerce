package com.algaworks.ecommerce.jpql;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Categoria;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PaginacaoJPQLTest extends EntityManagerTest {

    @Test
    public void paginarResultados() {
        String jpql = "select c from Categoria c order by c.nome";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);

        typedQuery.setFirstResult(5);
        typedQuery.setMaxResults(2);

        List<Categoria> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }

    @Test
    public void paginarResultadosComNativeQuery() {
        String sql = "SELECT * FROM categoria ORDER BY nome";

        Query nativeQuery = entityManager.createNativeQuery(sql, Categoria.class);

        nativeQuery.setFirstResult(5);
        nativeQuery.setMaxResults(2);

        List<Categoria> lista = nativeQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }

}
