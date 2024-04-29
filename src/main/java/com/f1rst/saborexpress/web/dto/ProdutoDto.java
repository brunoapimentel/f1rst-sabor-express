package com.f1rst.saborexpress.web.dto;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.web.validators.DataMaiorOuIgualAtual;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoDto {
    @NotBlank
    private String nome;
    @Min(1)
    private int quantidade;
    @DecimalMin("0.01")
    private double preco;
    @DataMaiorOuIgualAtual
    @NotNull
    private LocalDate validade;

    public Produto toEntity() {
        Produto produto = new Produto();

        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setValidade(validade);

        return produto;
    }
}
