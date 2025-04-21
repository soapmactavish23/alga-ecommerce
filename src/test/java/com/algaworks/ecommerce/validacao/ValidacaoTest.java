package com.algaworks.ecommerce.validacao;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Cliente;
import org.junit.Test;

public class ValidacaoTest extends EntityManagerTest {

    @Test
    public void validarCliente() {
        entityManager.getTransaction().begin();

        Cliente cliente = new Cliente();

        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
    }

}
