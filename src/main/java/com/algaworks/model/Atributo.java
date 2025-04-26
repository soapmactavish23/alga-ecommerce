package com.algaworks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Atributo {

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    private String valor;

}
