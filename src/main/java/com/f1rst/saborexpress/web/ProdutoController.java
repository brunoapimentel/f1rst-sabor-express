package com.f1rst.saborexpress.web;

import com.f1rst.saborexpress.repository.Produto;
import com.f1rst.saborexpress.service.ProdutoService;
import com.f1rst.saborexpress.web.dto.ProdutoDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(@RequestBody @Valid ProdutoDto produtoDto) {
        service.inserir(produtoDto.toEntity());
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> erros = new HashMap<>();

        for(ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            String atributo = ((FieldError) objectError).getField();
            erros.put(atributo, objectError.getDefaultMessage());
        }

        return erros;
    }
}
