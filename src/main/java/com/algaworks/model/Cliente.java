package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id
    private Integer id;

    private String nome;

}
