package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.model.Produto;
import org.junit.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest{

    @Test
    public void abrirEFecharATransacao(){

        Produto produto = new Produto();

        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.merge(produto);
        entityManager.remove(produto);

        entityManager.getTransaction().commit();

    }

}
