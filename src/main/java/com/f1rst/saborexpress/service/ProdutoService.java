package com.f1rst.saborexpress.service;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository repository;

    public void inserir(Produto produto) {
        repository.save(produto);
    }
}
