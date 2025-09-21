// Carrinho.java
package com.javapizzaria.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }
}
