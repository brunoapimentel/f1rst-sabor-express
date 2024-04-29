package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(@RequestBody Produto produto) {
        service.inserir(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }
}
