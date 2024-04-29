package com.f1rst.saborexpress.service;

import com.f1rst.saborexpress.DadosTeste;
import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Produto produto = DadosTeste.produto();

        service.inserir(produto);

        verify(repository).save(produto);
    }

    @Test
    void deveListarOsProdutos() {
        List<Produto> listaProdutos = DadosTeste.listaProdutos();

        when(repository.findAll()).thenReturn(listaProdutos);

        List<Produto> produtosRetornados = service.listar();

        assertEquals(listaProdutos, produtosRetornados);
    }
}
