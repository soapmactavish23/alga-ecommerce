package com.algaworks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Estoque {

    @Id
    private Integer id;

    private Integer produtoId;

    private Integer quantidade;

}
