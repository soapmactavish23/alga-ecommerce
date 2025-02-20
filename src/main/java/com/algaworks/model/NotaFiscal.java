package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}
