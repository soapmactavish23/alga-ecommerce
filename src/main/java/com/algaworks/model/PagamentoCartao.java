package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PagamentoCartao {

    @Id
    private Integer id;

    private Integer pedidoId;

    private StatusPagamento status;

    private String numero;


}
