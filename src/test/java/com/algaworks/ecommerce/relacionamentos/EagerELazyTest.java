package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.Pedido;
import org.junit.Test;

public class EagerELazyTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();
    }
}
