package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends EntidadeBaseInteger {

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String numero;


}
