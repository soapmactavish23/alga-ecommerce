package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

}
