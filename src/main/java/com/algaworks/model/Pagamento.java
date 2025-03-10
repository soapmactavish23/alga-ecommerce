package com.algaworks.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorColumn(name = "tipo_pagamento",
        discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pagamento")
public abstract class Pagamento extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}
