package com.algaworks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("cartao")
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    @Size(max = 50)
    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;
}