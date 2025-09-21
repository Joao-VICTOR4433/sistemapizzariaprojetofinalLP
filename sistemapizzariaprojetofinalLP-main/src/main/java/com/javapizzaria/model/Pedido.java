package com.javapizzaria.model;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private Cliente cliente;
    private Map<Produto, Integer> itens;
    private boolean finalizado = false;

    // Novos campos
    private boolean rodizio = false;
    private String enderecoEntrega;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new HashMap<>();
    }

    public Cliente getCliente() { return cliente; }
    public Map<Produto, Integer> getItens() { return itens; }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.put(produto, itens.getOrDefault(produto, 0) + quantidade);
    }

    // Novos métodos
    public void setRodizio(boolean rodizio) { this.rodizio = rodizio; }
    public void setEnderecoEntrega(String endereco) { this.enderecoEntrega = endereco; }

    public boolean isRodizio() { return rodizio; }
    public String getEnderecoEntrega() { return enderecoEntrega; }

    public void finalizar(String pagamento) {
        this.finalizado = true;
        System.out.println("Pedido finalizado. Pagamento: " + pagamento);
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido de " + cliente.getNome() + ":\n");
        itens.forEach((produto, quantidade) ->
                sb.append(produto.getNome()).append(" x").append(quantidade).append("\n")
        );
        if (rodizio) sb.append("Rodízio: Sim\n");
        if (enderecoEntrega != null && !enderecoEntrega.isEmpty()) sb.append("Entrega: ").append(enderecoEntrega).append("\n");
        return sb.toString();
    }
}
