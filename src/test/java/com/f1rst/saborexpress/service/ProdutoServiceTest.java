package com.f1rst.saborexpress.service;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class ProdutoServiceTest {
    private ProdutoService service;
    private ProdutoRepository repository;

    @BeforeEach
    public void preparar() {
        repository = Mockito.mock(ProdutoRepository.class);
        service = new ProdutoService(repository);
    }

    @Test
    void deveInserirProdutoCorretamente() {
        Produto produto = new Produto(
                0,
                "Banoff",
                5,
                30,
                LocalDate.of(2024, 5, 1)
        );

        service.inserir(produto);

        Mockito.verify(repository).save(produto);
    }
}