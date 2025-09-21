package com.javapizzaria.model;

public class Comida extends Produto {
    private String tipo;

    public Comida(String nome, double preco, String tipo) {
        super(nome, preco);
        this.tipo = tipo;
    }

    public String getTipo() {  // <-- adicione este método
        return tipo;
    }

    @Override
    public String toString() {
        return getNome() + " (R$ " + getPreco() + ") - " + tipo;
    }
}
