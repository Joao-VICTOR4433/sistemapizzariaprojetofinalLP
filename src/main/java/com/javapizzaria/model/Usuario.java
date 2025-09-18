package com.javapizzaria.model;

public abstract class Usuario {
    private static int contador = 1;
    private int id;
    private String nome;

    public Usuario(String nome) {
        this.id = contador++;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome;
    }
}
