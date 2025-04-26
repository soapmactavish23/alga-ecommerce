package com.algaworks.model;

import com.algaworks.listener.GenericoListener;
import com.algaworks.model.converter.BooleanToSimNaoConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Produto.listar", query = "select p from Produto p"),
        @NamedQuery(name = "Produto.listarPorCategoria", query = "select p from Produto p where exists (select 1 from Categoria c2 join c2.produtos p2 where p2 = p and c2.id = :categoria)")
})
@EntityListeners({ GenericoListener.class })
@Entity
@Table(name = "produto",
        uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) },
        indexes = { @Index(name = "idx_nome", columnList = "nome") })
public class Produto extends EntidadeBaseInteger {

    @NotNull
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @NotNull
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    private BigDecimal preco;

    @Lob
    private byte[] foto;

    @NotNull
    @Column(nullable = false, length = 3)
    @Convert(converter = BooleanToSimNaoConverter.class)
    private Boolean ativo = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_tag_produto")))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_atributo_produto")))
    private List<Atributo> atributos;
}