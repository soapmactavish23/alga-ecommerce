package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ItemPedido {

    @Id
    private Integer id;

    private Integer pedidoId;

    private Integer produtoId;

    private BigDecimal precoProduto;

    private Integer quantidade;

}
