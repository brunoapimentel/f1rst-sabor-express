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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

        mockMvc.perform(
                MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}