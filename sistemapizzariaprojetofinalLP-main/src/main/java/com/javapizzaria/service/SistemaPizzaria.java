package com.javapizzaria.service;

import com.javapizzaria.model.*;

import java.util.ArrayList;
import java.util.List;

public class SistemaPizzaria {
    private final List<Cliente> clientes;
    private final List<Produto> produtos;
    private final List<Pedido> pedidos;

    private final List<Funcionario> funcionarios;

    public SistemaPizzaria() {
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente c) { clientes.add(c); }
    public List<Cliente> getClientes() { return clientes; }

    public void cadastrarComida(String nome, double preco, String tipo) {
        produtos.add(new Comida(nome, preco, tipo));
    }

    public void cadastrarBebida(String nome, double preco, double volume) {
        produtos.add(new Bebida(nome, preco, volume));
    }

    public List<Produto> getProdutos() { return produtos; }

    public Pedido novoPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        pedidos.add(pedido);
        return pedido;
    }

    public void registrarPedido(Pedido pedido) {
        if(!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        }
    }

    public List<Pedido> getPedidos() { return pedidos; }

    public void cadastrarFuncionario(Funcionario f) { funcionarios.add(f); }
    public List<Funcionario> getFuncionarios() { return funcionarios; }
}
