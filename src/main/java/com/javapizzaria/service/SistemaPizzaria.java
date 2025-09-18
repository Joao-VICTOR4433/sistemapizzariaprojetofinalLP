package com.javapizzaria.service;

import com.javapizzaria.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class SistemaPizzaria {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    // Clientes
    public void cadastrarCliente(Cliente cliente) throws Exception {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(cliente.getNome())) {
                throw new Exception("Cliente já cadastrado: " + cliente.getNome());
            }
        }
        clientes.add(cliente);
        System.out.println("✅ Cliente cadastrado: " + cliente.getNome());
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    //  Produtos
    public void cadastrarComida(String nome, double preco, String tipo) {
        produtos.add(new Comida(nome, preco, tipo));
    }

    public void cadastrarBebida(String nome, double preco, double volume) {
        produtos.add(new Bebida(nome, preco, volume));
    }

    public List<Produto> getProdutos() { return produtos; }

    public Produto buscarProdutoPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    //Pedidos 
    public Pedido novoPedido(Cliente cliente) throws Exception {
        if (!clientes.contains(cliente)) {
            throw new Exception("Cliente não cadastrado: " + cliente.getNome());
        }
        Pedido pedido = new Pedido(cliente);
        pedidos.add(pedido);
        return pedido;
    }

    public void registrarPedido(Pedido pedido) throws Exception {
        if (pedido.getItens().isEmpty()) {
            throw new Exception("Pedido não possui produtos!");
        }
        pedidos.add(pedido);
        System.out.println("✅ Pedido registrado para " + pedido.getCliente().getNome());
    }

    public List<Pedido> getPedidos() { return pedidos; }

    //  Relatório
    public void gerarRelatorio(String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (Pedido p : pedidos) {
                writer.write(p.toString() + "\n\n");
            }
        }
        System.out.println("📄 Relatório gerado em: " + caminhoArquivo);
    }
}
