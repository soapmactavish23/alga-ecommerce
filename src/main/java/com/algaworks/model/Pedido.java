package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Pedido {

    @Id
    private Integer id;

    private LocalDateTime dataPedido;

    private LocalDateTime dataConclusao;

    private Integer notaFiscalId;

    private BigDecimal total;

    private StatusPedido status;


}
