package com.prog.web.domain.categoria;

import com.prog.web.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_categoria")
@JsonIgnoreProperties({"produtos"})
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_categoria_id_seq")
    @SequenceGenerator(name = "tb_categoria_id_seq", sequenceName = "tb_categoria_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
