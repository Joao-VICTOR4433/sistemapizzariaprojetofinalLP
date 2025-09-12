package com.javapizzaria.service;

import com.javapizzaria.model.Cliente;
import com.javapizzaria.model.Pedido;
import com.javapizzaria.model.Produto;

import java.io.IOException;
import java.util.List;

public interface SistemaInterface {

    void cadastrarCliente(Cliente cliente) throws Exception;

    Pedido criarPedido(Cliente cliente) throws Exception;

    void adicionarProdutoAoPedido(Pedido pedido, Produto produto, int quantidade) throws Exception;

    void finalizarPedido(Pedido pedido, String formaPagamento) throws Exception;

    void gerarRelatorio(List<Pedido> pedidos, String caminhoArquivo) throws IOException;
}
