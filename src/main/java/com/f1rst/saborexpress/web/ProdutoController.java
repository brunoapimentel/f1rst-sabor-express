package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(Produto produto) {
        service.inserir(produto);
    }
}
