package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ElementoCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }

}
