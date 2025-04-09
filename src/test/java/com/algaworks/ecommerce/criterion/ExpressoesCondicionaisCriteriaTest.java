package com.algaworks.ecommerce.criterion;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Cliente;
import com.algaworks.model.Produto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {

    @Test
    public void usarMaiorMenor() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("preco"), new BigDecimal(799)),
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("preco"), new BigDecimal(3500)));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println(
                "ID: " + p.getId() + ", Nome: " + p.getNome() + ", Preço: " + p.getPreco()));
    }

    @Test
    public void usarIsEmpty() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isEmpty(root.get("categorias")));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();
        Assert.assertTrue(produtos.isEmpty());

        produtos.forEach(produto -> {
            System.out.println(produto);
        });
    }

    @Test
    public void usarIsNull() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isNull(root.get("foto")));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();
        Assert.assertFalse(produtos.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLike() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),"%a%"));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Cliente> clientes = typedQuery.getResultList();
        Assert.assertFalse(clientes.isEmpty());
    }

}
