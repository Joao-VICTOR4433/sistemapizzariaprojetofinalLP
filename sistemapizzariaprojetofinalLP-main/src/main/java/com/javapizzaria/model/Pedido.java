package com.javapizzaria.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pedido {
    private Cliente cliente;
    private Map<Produto, Integer> itens;
    private boolean finalizado = false;

    private boolean rodizio = false;
    private String enderecoEntrega;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new HashMap<>();
    }

    public Cliente getCliente() { return cliente; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
     Pedido pedido = (Pedido) o;
       return finalizado == pedido.finalizado && rodizio == pedido.rodizio && Objects.equals(cliente, pedido.cliente)
        && Objects.equals(itens, pedido.itens) && Objects.equals(enderecoEntrega, pedido.enderecoEntrega);
    }

   @Override
    public int hashCode() {
        return Objects.hash(cliente, itens, finalizado, rodizio, enderecoEntrega);
   }

    public Map<Produto, Integer> getItens() { return itens; }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.put(produto, itens.getOrDefault(produto, 0) + quantidade);
    }

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
                sb.append(produto.getNome()).append(" x").append(quantidade).append("\n"));
        if (rodizio) sb.append("Rodízio: Sim\n");
        if (enderecoEntrega != null && !enderecoEntrega.isEmpty()) sb.append("Entrega: ").append(enderecoEntrega).append("\n");
        return sb.toString();
    }
}
