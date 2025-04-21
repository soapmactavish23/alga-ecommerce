package com.algaworks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class EnderecoEntregaPedido {

    @Size(max = 9)
    @Column(length = 9)
    private String cep;

    @Size(max = 100)
    @Column(length = 100)
    private String logradouro;

    @Size(max = 10)
    @Column(length = 10)
    private String numero;

    @Size(max = 50)
    @Column(length = 50)
    private String complemento;

    @Size(max = 50)
    @Column(length = 50)
    private String bairro;

    @Size(max = 50)
    @Column(length = 50)
    private String cidade;

    @Size(max = 2)
    @Column(length = 2)
    private String estado;
}