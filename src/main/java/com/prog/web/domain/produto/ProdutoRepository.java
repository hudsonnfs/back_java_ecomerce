package com.prog.web.domain.produto;

import com.prog.web.domain.categoria.Categoria;
import com.prog.web.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.categoria = :categoria")
    public List<Produto> buscarProdutosPorCategoria(@Param("categoria") Categoria categoria);

    /**
     * Recupera o produto a partir do nome informado.
     *
     * @param nome
     * @return
     */
    public Produto findByNome(String nome);

}
