package com.algaworks.ecommerce.criterion;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Cliente;
import com.algaworks.model.ItemPedido;
import com.algaworks.model.Pedido;
import com.algaworks.model.Produto;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class SubqueriesCriteriaTest extends EntityManagerTest {

    @Test
    public void pesquisarComAny02() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<ItemPedido> subqueryRoot = subquery.from(ItemPedido.class);
        subquery.select(subqueryRoot.get("precoProduto"));
        subquery.where(criteriaBuilder.equal(subqueryRoot.get("produto"), root));

        criteriaQuery.where(
                criteriaBuilder.notEqual(
                        root.get("preco"), criteriaBuilder.any(subquery))
        );

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));

    }

    @Test
    public void pesquisarComAny01() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<ItemPedido> subqueryRoot = subquery.from(ItemPedido.class);
        subquery.select(subqueryRoot.get("precoProduto"));
        subquery.where(criteriaBuilder.equal(subqueryRoot.get("produto"), root));

        criteriaQuery.where(criteriaBuilder.equal(root.get("preco"), criteriaBuilder.any(subquery)));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();
        Assert.assertFalse(produtos.isEmpty());

        produtos.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComAll02() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<ItemPedido> subqueryRoot = subquery.from(ItemPedido.class);
        subquery.select(subqueryRoot.get("precoProduto"));
        subquery.where(criteriaBuilder.equal(subqueryRoot.get("produto"), root));

        criteriaQuery.where(
                criteriaBuilder.greaterThan(root.get("preco"), criteriaBuilder.all(subquery)),
                criteriaBuilder.exists(subquery));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComExists() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<ItemPedido> subqueryRoot = subquery.from(ItemPedido.class);
        subquery.select(criteriaBuilder.literal(1));
        subquery.where(criteriaBuilder.equal(subqueryRoot.get("produto"), root));

        criteriaQuery.where(criteriaBuilder.exists(subquery));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();
        Assert.assertFalse(produtos.isEmpty());

        produtos.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComIN() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<ItemPedido> subqueryRoot = subquery.from(ItemPedido.class);
        Join<ItemPedido, Pedido> subqueryJoinPedido = subqueryRoot.join("pedido");
        Join<ItemPedido, Produto> subqueryJoinProduto = subqueryRoot.join("produto");
        subquery.select(subqueryJoinPedido.get("id"));
        subquery.where(criteriaBuilder.greaterThan(subqueryJoinProduto.get("preco"), new BigDecimal(100)));

        criteriaQuery.where(root.get("id").in(subquery));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());

        pedidos.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueries03() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Pedido> subqueryRoot = subquery.from(Pedido.class);
        subquery.select(criteriaBuilder.sum(subqueryRoot.get("total")));
        subquery.where(criteriaBuilder.equal(root, subqueryRoot.get("cliente")));

        criteriaQuery.where(criteriaBuilder.greaterThan(subquery, new BigDecimal(1300)));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Nome: " + obj.getNome()));

    }

    @Test
    public void pesquisarSubqueries02() {
//         Todos os pedidos acima da média de vendas
//        String jpql = "select p from Pedido p where " +
//                " p.total > (select avg(total) from Pedido)";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Pedido> subqueryRoot = subquery.from(Pedido.class);
        subquery.select(criteriaBuilder.avg(subqueryRoot.get("total")).as(BigDecimal.class));

        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("total"), subquery));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(
                "ID: " + obj.getId() + ", Total: " + obj.getTotal()));
    }

    @Test
    public void pesquisarSubqueries01() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Produto> subqueryRoot = subquery.from(Produto.class);
        subquery.select(criteriaBuilder.max(subqueryRoot.get("preco")));

        criteriaQuery.where(criteriaBuilder.equal(root.get("preco"), subquery));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();
        Assert.assertFalse(produtos.isEmpty());

        produtos.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Nome: " + obj.getNome() + ", Preço: " + obj.getPreco()));

    }

}
