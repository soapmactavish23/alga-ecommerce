package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.iniciandocomjpa.EntityManagerTest;
import com.algaworks.model.EnderecoEntregaPedido;
import com.algaworks.model.Pedido;
import com.algaworks.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutido extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {
        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("00000-000");
        endereco.setLogradouro("Rua principal");
        endereco.setBairro("Bairro principal");
        endereco.setComplemento("Complemento principal");
        endereco.setCidade("Estado Paulo");
        endereco.setEstado("Paulo");

        Pedido pedido = new Pedido();
        //pedido.setId(1);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEndereco(endereco);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificado = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificado);
        Assert.assertNotNull(pedidoVerificado.getEndereco());
        Assert.assertNotNull(pedidoVerificado.getEndereco().getLogradouro());

    }

}
