package com.f1rst.saborexpress.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    List<Produto> findAll();
}
