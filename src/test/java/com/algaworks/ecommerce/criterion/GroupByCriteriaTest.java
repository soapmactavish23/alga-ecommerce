package com.algaworks.ecommerce.criterion;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Categoria;
import com.algaworks.model.ItemPedido;
import com.algaworks.model.Produto;
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

}
