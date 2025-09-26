package com.javapizzaria.service;

import com.javapizzaria.Exception.ClienteJaCadastradoException;
import com.javapizzaria.Exception.*;

import com.javapizzaria.model.Cliente;
import com.javapizzaria.model.Pedido;
import com.javapizzaria.model.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaPizzariaImpl implements SistemaInterface {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(cliente.getNome())) {
                throw new ClienteJaCadastradoException("Cliente já cadastrado: " + cliente.getNome());
            }
        }
        clientes.add(cliente);
        System.out.println("✅ Cliente cadastrado: " + cliente.getNome());
    }

    @Override
    public Pedido criarPedido(Cliente cliente) throws ClienteNaoEncontradoException {
        if (!clientes.contains(cliente)) {
            throw new ClienteNaoEncontradoException("Cliente não cadastrado: " + cliente.getNome());
        }
        Pedido pedido = new Pedido(cliente);
        pedidos.add(pedido);
        return pedido;
    }

    @Override
    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que 0");
        }
        pedido.adicionarProduto(produto, quantidade);
        System.out.println("✅ Produto adicionado: " + produto.getNome() + " x" + quantidade);
    }

    @Override
    public void finalizarPedido(Pedido pedido, String formaPagamento) throws ProdutoNaoEncontradoException {
        if (pedido.getItens().isEmpty()) {
            throw new ProdutoNaoEncontradoException("Pedido sem produtos, não pode finalizar");
        }
        pedido.finalizar(formaPagamento);
        System.out.println("✅ Pedido finalizado para: " + pedido.getCliente().getNome());
    }

    @Override
    public void gerarRelatorio(List<Pedido> pedidos, String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (Pedido p : pedidos) {
                writer.write(p.toString() + "\n\n");
            }
        }
        System.out.println("📄 Relatório gerado em: " + caminhoArquivo);
    }

    public List<Cliente> getClientes() { return clientes; }
    public List<Pedido> getPedidos() { return pedidos; }
}
