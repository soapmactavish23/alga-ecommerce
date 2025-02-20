package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
    @TableGenerator(name = "tabela",
            table = "hibernate_sequences",
            pkColumnName = "sequence_name",
            pkColumnValue = "categoria",
            valueColumnName = "next_val",
            initialValue = 0,
            allocationSize = 50
    )
    private Integer id;

    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

}
