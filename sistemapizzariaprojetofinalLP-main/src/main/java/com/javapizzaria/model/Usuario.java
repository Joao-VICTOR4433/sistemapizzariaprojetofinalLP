package com.javapizzaria.model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Usuario {
    private static final AtomicInteger SEQUENCIAL = new AtomicInteger(1);
    private final int id;
    private String nome;

    public Usuario(String nome) {
        this.id = SEQUENCIAL.getAndIncrement();
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
