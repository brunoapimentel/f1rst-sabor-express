package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.DadosTeste;
import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    ObjectMapper mapper;

    @BeforeEach
    public void preparar() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deveInserirUmProdutoCorretamente() throws Exception {
        Produto produto = DadosTeste.produto();
        String produtoJson = mapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(
            post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
            .andExpect(status().isCreated());

        verify(produtoService).inserir(produto);
    }

    @Test
    void deveListarOsProdutos() throws Exception {
        List<Produto> listaProdutos = DadosTeste.listaProdutos();

        when(produtoService.listar()).thenReturn(listaProdutos);

        mockMvc.perform(get("/produto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].nome").value("Banoff"))
                .andExpect(jsonPath("$[1].quantidade").value("5"))
                .andExpect(jsonPath("$[1].preco").value("20.0"))
                .andExpect(jsonPath("$[1].validade").value("2024-07-01"));
    }
}
