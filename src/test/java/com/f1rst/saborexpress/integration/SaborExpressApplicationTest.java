package com.f1rst.saborexpress.integration;

import com.f1rst.saborexpress.DadosTeste;
import com.f1rst.saborexpress.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaborExpressApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProdutoRepository repository;

    ObjectMapper mapper;

    @AfterEach
    public void limparBanco() {
        repository.deleteAll();
    }

    @BeforeEach
    public void preparar() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    @Sql("/produtos.sql")
    public void deveListarOsProdutos() throws JsonProcessingException {
        String url = String.format("http://localhost:%d/produto", port);

        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);

        String respostaEsperada = mapper.writeValueAsString(DadosTeste.listaProdutos());

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada, resposta.getBody());
    }
}
