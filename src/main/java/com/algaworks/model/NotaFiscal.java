package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Lob
    @Column(length = 1000)
    private byte[] xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}
