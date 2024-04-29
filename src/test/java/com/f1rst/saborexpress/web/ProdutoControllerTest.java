package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.DadosTeste;
import com.f1rst.saborexpress.service.ProdutoService;
import com.f1rst.saborexpress.web.dto.ProdutoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void deveInserirUmProdutoCorretamente() throws Exception {
        ProdutoDto produto = DadosTeste.produtoDto();
        String produtoJson = mapper.writeValueAsString(produto);

        mockMvc.perform(
                post("/produto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(produtoJson))
                .andExpect(status().isCreated());
    }


    @Test
    public void deveValidarUmProduto() throws Exception {
        ProdutoDto produto = new ProdutoDto();
        produto.setQuantidade(-1);
        produto.setValidade(LocalDate.of(1980,1,1));

        String produtoJson = mapper.writeValueAsString(produto);

        mockMvc.perform(
            post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.nome").value("must not be blank"))
            .andExpect(jsonPath("$.preco").value("must be greater than or equal to 0.1"))
            .andExpect(jsonPath("$.quantidade").value("must be greater than or equal to 0"))
            .andExpect(jsonPath("$.validade").value("must be equal or greater than current date"));

    }
}
