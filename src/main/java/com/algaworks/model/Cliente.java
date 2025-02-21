package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
