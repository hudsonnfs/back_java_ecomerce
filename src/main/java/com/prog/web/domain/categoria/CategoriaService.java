package com.prog.web.domain.categoria;

import com.prog.web.core.exceptions.CustomNotFoundException;
import com.prog.web.core.exceptions.DuplicatedException;
import com.prog.web.core.exceptions.RegistryVinculateException;
import com.prog.web.domain.produto.Produto;
import com.prog.web.core.exceptions.UnprocessableEntityException;
import com.prog.web.domain.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de 'CategoriaService'.
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoService produtoService;

    /**
     * Recupera a lista de categorias cadastradas no sistema.
     *
     * @return List<Categoria>
     */
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    /**
     * Salva um novo registro ou atualiza um registro ja existente no banco de dados.
     *
     * @param categoria
     */
    public Categoria salvar(Categoria categoria) {
        this.validarCamposObrigatorios(categoria);
        Categoria categoriaSalva = null;

        if (categoria.getId() != 0) {

            Categoria c = this.buscarPorId(categoria.getId());

            if (c != null) {
                categoriaSalva = categoriaRepository.save(categoria);
            } else {
                throw new CustomNotFoundException("Categoria nao encontrada.");
            }
        } else {

            Categoria c = this.categoriaRepository.findByNome(categoria.getNome());

            if (c != null) {
                throw new DuplicatedException("Categoria ja cadastrada.");
            }

            categoriaSalva = categoriaRepository.save(categoria);
        }

        return categoriaSalva;
    }

    /**
     * Remove uma categoria de acordo com o id informado.
     *
     * @param id
     */
    public void deletar(long id) {
        Categoria categoria = categoriaRepository.findOne(id);

        if (categoria == null) {
            throw new CustomNotFoundException("Categoria nao encontrada.");
        }

        if (categoria.getProdutos().size() > 0) {
            throw new RegistryVinculateException("Existem produtos vinculados a categoria.");
        }

        categoriaRepository.delete(id);
    }

    /**
     * Recupera os dados de uma categoria de acordo com o id informado.
     *
     * @param id
     * @return Categoria
     */
    public Categoria buscarPorId(long id) {
        Categoria categoria = categoriaRepository.findOne(id);

        if (categoria == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado.");
        }

        return categoria;
    }

    /**
     * Deleta todos os produtos vinculados a categoria informada.
     *
     * @param id
     */
    public void deletarProdutosVinculados(long id) {
        Categoria categoria = this.buscarPorId(id);

        if(categoria == null) {
            throw new CustomNotFoundException("Categoria nao encontrada.");
        }

        for (Produto p : categoria.getProdutos()) {
            produtoService.deletar(p.getId());
        }
    }

    /**
     * Valida o preenchimento dos campos obrigatorios.
     *
     * @param categoria
     */
    private void validarCamposObrigatorios(Categoria categoria) {
        boolean existeErros = false;

        if (categoria.getNome() == null) {
            existeErros = true;
        }

        if (existeErros) {
            throw new UnprocessableEntityException();
        }
    }

}
