package com.algaworks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "cliente")
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
public class Cliente extends EntidadeBaseInteger {

    private String nome;

    @ElementCollection
    @Column(name = "descricao")
    @MapKeyColumn(name = "tipo")
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    private Map<String, String> contatos;

    @Transient
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    @Column(table = "cliente_detalhe")
    private SexoCliente sexo;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }

}
