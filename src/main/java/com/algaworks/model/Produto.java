package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Produto {

    @Id
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

}
