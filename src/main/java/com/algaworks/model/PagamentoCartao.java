package com.algaworks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

    @Id
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    private StatusPagamento status;

    private String numero;


}
