package com.javapizzaria.model;

public class Bebida extends Produto {
    private double volume;

    public Bebida(String nome, double preco, double volume) {
        super(nome, preco);
        this.volume = volume;
    }

    public double getVolume() { return volume; }
}
