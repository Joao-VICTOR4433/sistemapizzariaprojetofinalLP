package com.javapizzaria.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido {
    private Cliente cliente;
    private Map<Produto, Integer> itens = new LinkedHashMap<>();
    private String formaPagamento;
    private boolean finalizado = false;

    private boolean rodizio = false;
    private String enderecoEntrega = null;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.put(produto, itens.getOrDefault(produto, 0) + quantidade);
    }

    public double getValorTotal() {
        return itens.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPreco() * e.getValue())
                .sum();
    }

    public void finalizar(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.finalizado = true;
    }

    
    public boolean isRodizio() { return rodizio; }
    public void setRodizio(boolean rodizio) { this.rodizio = rodizio; }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public Cliente getCliente() { return cliente; }
    public Map<Produto, Integer> getItens() { return itens; }
    public String getFormaPagamento() { return formaPagamento; }
    public boolean isFinalizado() { return finalizado; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        if(rodizio) sb.append("Rodízio: Sim\n");
        if(enderecoEntrega != null && !enderecoEntrega.isEmpty()) sb.append("Endereço de entrega: ").append(enderecoEntrega).append("\n");
        sb.append("Produtos:\n");
        for (Map.Entry<Produto, Integer> e : itens.entrySet()) {
            sb.append("- ").append(e.getKey().getNome())
                    .append(" x").append(e.getValue())
                    .append(" R$").append(e.getKey().getPreco() * e.getValue())
                    .append("\n");
        }
        sb.append("Total: R$").append(getValorTotal()).append("\n");
        sb.append("Pagamento: ").append(formaPagamento).append("\n");
        sb.append("Finalizado: ").append(finalizado);
        return sb.toString();
    }
}
