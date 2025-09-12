// Comida.java
package com.javapizzaria.model;

public class Comida extends Produto {
    private String tipo;

    public Comida(String nome, double preco, String tipo) {
        super(nome, preco);
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }
}
