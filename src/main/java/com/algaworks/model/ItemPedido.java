package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;

}
