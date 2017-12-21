package com.prog.web.domain.produto;

import com.prog.web.core.exceptions.CustomNotFoundException;
import com.prog.web.core.exceptions.DuplicatedException;
import com.prog.web.domain.categoria.Categoria;
import com.prog.web.domain.produto.Produto;
import com.prog.web.core.exceptions.UnprocessableEntityException;
import com.prog.web.domain.categoria.CategoriaRepository;
import com.prog.web.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Recupera a lista de produtos cadastradas no sistema.
     *
     * @return List<Categoria>
     */
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    /**
     * Salva um novo registro ou atualiza um registro ja existente no banco de dados.
     *
     * @param produto
     */
    public Produto salvar(Produto produto) {
        this.validaCamposObrigatorios(produto);
        Produto produtoSalvo = null;

        if (produto.getId() != 0) {
            Produto p = produtoRepository.findOne(produto.getId());

            if (produto.getCategoria() == null) {
                produto.setCategoria(p.getCategoria());
            }

            if (p != null) {
                produtoSalvo = produtoRepository.save(produto);
            } else {
                throw new CustomNotFoundException("Produto nao encontrado");
            }
        } else {

            Produto p = this.produtoRepository.findByNome(produto.getNome());

            if (p != null) {
                throw new DuplicatedException("Produto ja cadastrado.");
            }

            produtoSalvo = produtoRepository.save(produto);
        }

        return produtoSalvo;
    }

    /**
     * Remove um produto de acordo com o id informado.
     *
     * @param id
     */
    public void deletar(long id) {
        Produto produto = produtoRepository.findOne(id);

        if (produto == null) {
            throw new CustomNotFoundException("Produto nao encontrado.");
        }

        produtoRepository.delete(id);
    }

    /**
     * Recupera os dados de uma produto de acordo com o id informado.
     *
     * @param id
     * @return Categoria
     */
    public Produto buscarPorId(long id) {
        Produto p = produtoRepository.findOne(id);

        if (p == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado");
        }

        return p;
    }

    /**
     * Recupera a lista de produtos de acordo com a categoria informada.
     *
     * @param id
     * @return List<Produto>
     */
    public List<Produto> buscarProdutosPorCategoria(long id) {
        Categoria categoria = this.categoriaRepository.findOne(id);

        if (categoria == null) {
            throw new CustomNotFoundException("Categoria nao encontrada");
        }

        return this.produtoRepository.buscarProdutosPorCategoria(categoria);
    }

    /**
     * Valida se os campos obrigatorios foram informados corretamente.
     *
     * @param produto
     * @return
     */
    private void validaCamposObrigatorios(Produto produto) {
        boolean existeErros = false;

        if (produto.getNome() == null) {
            existeErros = true;
        }

        if (produto.getDescricao() == null) {
            existeErros = true;
        }

        if (Double.isNaN(produto.getPreco())) {
            existeErros = true;
        }

        if (existeErros) {
            throw new UnprocessableEntityException();
        }
    }
}
