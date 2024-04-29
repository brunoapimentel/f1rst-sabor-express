package com.f1rst.saborexpress;

import com.f1rst.saborexpress.repository.Produto;

import java.time.LocalDate;
import java.util.List;

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

    public static List<Produto> listaProdutos() {
        return List.of(
            new Produto(
                1,
                "Brownie",
                10,
                15.0,
                LocalDate.of(2024, 6, 1)

            ),
            new Produto(
                2,
                "Banoff",
                5,
                20.0,
                LocalDate.of(2024,7,1)
            )
        );
    }
}
