package com.prog.web.domain.produto;

import com.prog.web.core.controller.RestAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins = "*")
public class ProdutoController extends RestAbstractController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Recupera a lista de produtos do sistema.
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> listar() {
        List<Produto> produtos = this.produtoService.listar();
        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoConsulta);
        return jsonResponse(produtos);
    }

    /**
     * Recupera o produto pelo atributo 'id' informado.
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
        Produto produto = this.produtoService.buscarPorId(id);
        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoConsulta);
        return jsonResponse(produto);
    }

    // TODO alterar isso para categoria : /caategoria/1/produto
    @GetMapping(value = "categoria/{categoria_id}")
    public ResponseEntity<?> getProdutosPorCategoria(@PathVariable("categoria_id") long categoria_id) {
        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoConsulta);
        return jsonResponse(this.produtoService.buscarProdutosPorCategoria(categoria_id));
    }

    /**
     * Insere um novo produto.
     *
     * @param produto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Produto produto) {
        Produto produtoSalvo = this.produtoService.salvar(produto);

        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoSalvar);
        return jsonResponse(produtoSalvo);
    }

    /**
     * Atualiza um produto de acordo com o atributo 'id' informado.
     *
     * @param produto
     * @param id
     * @return
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<?> atualizar(@RequestBody Produto produto, @PathVariable("id") long id) {
        produto.setId(id);
        Produto produtoSalvo = produtoService.salvar(produto);

        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoSalvar);
        return jsonResponse(produtoSalvo);
    }

    /**
     * Remove um produto de acordo com o atributo 'id' informado.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") long id) {
        this.produtoService.deletar(id);

        jsonResponseService.addSuccess(ProdutoMessageInterface.msgSucessoRemover);
        return jsonResponse(null);
    }

}
