package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class NotaFiscal {

    @Id
    private Integer id;

    private Integer pedidoId;

    private String xml;

    private Date dataEmissao;

}
