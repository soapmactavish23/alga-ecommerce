package com.algaworks.ecommerce.criterion;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class FuncoesCriteriaTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoAgregamento() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.avg(root.get("total")),
                criteriaBuilder.sum(root.get("total")),
                criteriaBuilder.min(root.get("total")),
                criteriaBuilder.max(root.get("total"))
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                    + ", avg: " + arr[1]
                    + ", count: " + arr[2]
                    + ", sum: " + arr[3]
                    + ", min: " + arr[4]
                    + ", max: " + arr[5]
        ));
    }

    @Test
    public void aplicarFuncaoNativa() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
                root.get("id"),
                criteriaBuilder.function("dayname", String.class, root.get("dataCriacao"))
        );

        criteriaQuery.where(criteriaBuilder.isTrue(
                criteriaBuilder.function("acima_media_faturamento", Boolean.class, root.get("total"))
        ));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0] + ", dayname: " + arr[1]));
    }

    @Test
    public void aplicarFuncaoColecao() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
            root.get("id"),
            criteriaBuilder.size(root.get("itens")));

        criteriaQuery.where(criteriaBuilder.greaterThan(criteriaBuilder.size(root.get("itens")), 1));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                    + ", size:" + arr[1]
        ));
    }

    @Test
    public void aplicarFuncaoNumero() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
                root.get("id"),
                criteriaBuilder.abs(criteriaBuilder.prod(root.get("id"), -1)),
                criteriaBuilder.mod(root.get("id"), 2),
                criteriaBuilder.sqrt(root.get("total"))
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                    + ", abs: " + arr[1]
                    + ", mod: " + arr[2]
                    + ", sqrt: " + arr[3]
        ));


    }

    @Test
    public void aplicarFuncaoData() {
        // current_date, current_time, current_timestamp

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        Join<Pedido, PagamentoBoleto> joinPagamentoBoleto = criteriaBuilder
                .treat(joinPagamento, PagamentoBoleto.class);

        criteriaQuery.multiselect(
                root.get("id"),
                criteriaBuilder.currentDate(),
                criteriaBuilder.currentTime(),
                criteriaBuilder.currentTimestamp()
        );

        criteriaQuery.where(
                criteriaBuilder.between(criteriaBuilder.currentDate(),
                        root.get("dataCriacao").as(java.sql.Date.class),
                        joinPagamentoBoleto.get("dataVencimento").as(java.sql.Date.class)),
                criteriaBuilder.equal(root.get("status"), StatusPedido.AGUARDANDO)
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", current_date: " + arr[1]
                        + ", current_time: " + arr[2]
                        + ", current_timestamp: " + arr[3]));
    }

    @Test
    public void aplicarFuncoesString() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        Path fieldName = root.get("nome");

        criteriaQuery.multiselect(
                root.get("nome"),
                criteriaBuilder.concat("Nome do cliente: ", fieldName),
                criteriaBuilder.length(fieldName),
                criteriaBuilder.locate(fieldName, "a"),
                criteriaBuilder.substring(fieldName, 1, 2),
                criteriaBuilder.lower(fieldName),
                criteriaBuilder.upper(fieldName),
                criteriaBuilder.trim(fieldName)
        );

        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.substring(fieldName, 1, 1), "M"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                    + ", concat: " + arr[1]
                    + ", length: " + arr[2]
                    + ", locale: " + arr[3]
                    + ", substring: " + arr[4]
                    + ", lower: " + arr[5]
                    + ", upper: " + arr[6]
                    + ", trim: " + arr[7]
        ));

    }

}
