package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest{

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone Rode Vedimic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle paperwhite 2° geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle paperwhite 2° geração", produtoVerificado.getNome());
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void abrirEFecharATransacao() {

//        Produto produto = new Produto();
//
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);
//
//        entityManager.getTransaction().commit();

    }

}
