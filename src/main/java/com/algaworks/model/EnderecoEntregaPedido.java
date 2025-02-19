package com.algaworks.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EnderecoEntregaPedido {

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

}
