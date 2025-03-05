package com.algaworks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

    @Column(name = "pedido_id")
    @EqualsAndHashCode.Include
    private Integer pedidoId;

    @Column(name = "produto_id")
    @EqualsAndHashCode.Include
    private Integer produtoId;



}
