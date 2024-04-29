package com.f1rst.saborexpress.web.dto;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.service.validators.DataMaiorOuIgualAtual;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
    @NotBlank
    private String nome;
    @Min(0)
    private int quantidade;
    @DecimalMin("0.1")
    private double preco;
    @DataMaiorOuIgualAtual
    @JsonFormat(pattern = "yyyy-dd-MM")
    private LocalDate validade;

    public Produto toEntity(){
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setValidade(validade);
        return produto;
    }
}
