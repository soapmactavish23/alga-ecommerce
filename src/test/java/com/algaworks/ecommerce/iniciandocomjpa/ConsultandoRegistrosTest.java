package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.*;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);
        //Produto produto = entityManager.getReference(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }

}
