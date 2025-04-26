package com.algaworks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@DiscriminatorValue("boleto")
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

    @Size(max = 100)
    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

}