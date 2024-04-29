package com.f1rst.saborexpress;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.web.dto.ProdutoDto;

import java.time.LocalDate;

public class DadosTeste {
    public static Produto produto() {
        return new Produto(
                0,
                "Banoff",
                5,
                30,
                LocalDate.of(2024, 5, 1)
        );
    }

    public static ProdutoDto produtoDto() {
        return new ProdutoDto(
                "Banoff",
                5,
                30,
                LocalDate.of(2024, 5, 1)
        );
    }
}
