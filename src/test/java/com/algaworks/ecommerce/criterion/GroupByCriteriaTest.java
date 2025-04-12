package com.algaworks.ecommerce.criterion;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.Test;

import java.util.List;

public class GroupByCriteriaTest extends EntityManagerTest {

    @Test
    public void agruparResultado01() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> joinProduto = root.join("produto");
        Join<Produto, Categoria> joinProdutoCategoria = joinProduto.join("categorias");

        criteriaQuery.multiselect(
            joinProdutoCategoria.get("nome"),
            criteriaBuilder.sum(root.get("precoProduto"))
        );

        criteriaQuery.groupBy(joinProdutoCategoria.get("id"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();
        lista.forEach(arr -> System.out.println("Nome categoria: " + arr[0] + ", sum: " + arr[1]));

    }

    @Test
    public void agruparResultado03Exercicio() {
//        Total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.pedido p join p.cliente c " +
//                " group by c.id";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Pedido> joinPedido = root.join("pedido");
        Join<Pedido, Cliente> joinPedidoCliente = joinPedido.join("cliente");

        criteriaQuery.multiselect(
                joinPedidoCliente.get("nome"),
                criteriaBuilder.sum(root.get("precoProduto"))
        );

        criteriaQuery.groupBy(joinPedidoCliente.get("id"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println("Nome cliente: " + arr[0] + ", Sum: " + arr[1]));
    }

}
