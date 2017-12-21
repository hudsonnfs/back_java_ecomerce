package com.prog.web.domain.categoria;

import com.prog.web.core.controller.RestAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends RestAbstractController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Recupera a lista de categorias do sistema.
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Categoria> categorias = this.categoriaService.listar();
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoConsulta);
        return jsonResponse(categorias);
    }

    /**
     * Recupera a categoria de acordo com o atributo 'id' informado.
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
        Categoria categoria = this.categoriaService.buscarPorId(id);
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoConsulta);
        return jsonResponse(categoria);
    }

    /**
     * Insere uma nova categoria.
     *
     * @param categoria
     * @return
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoSalvar);
        return jsonResponse(categoriaSalva);
    }

    /**
     * Atualiza uma categoria de acordo com o atributo 'id' informado.
     *
     * @param categoria
     * @param id
     * @return
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<?> atualizar(@RequestBody Categoria categoria, @PathVariable("id") long id) {
        categoria.setId(id);
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoSalvar);
        return jsonResponse(categoriaSalva);
    }

    /**
     * Remove uma categoria de acordo com o atributo 'id' informado.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") long id) {
        this.categoriaService.deletar(id);
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoRemover);
        return jsonResponse(null);
    }

    /**
     * Remove todos os produtos vunculados a categoria com o atributo 'id' informado.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}/produto")
    public ResponseEntity<?> deletarProdutosVinculados(@PathVariable("id") long id) {
        this.categoriaService.deletarProdutosVinculados(id);
        jsonResponseService.addSuccess(CategoriaMessageInterface.msgSucessoRemover);
        return jsonResponse(null);
    }

}

