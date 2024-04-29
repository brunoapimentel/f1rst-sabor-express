package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.service.ProdutoService;
import com.f1rst.saborexpress.web.dto.ProdutoDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(@Valid @RequestBody ProdutoDto produto) {
        service.inserir(produto.toEntity());
    }
}
