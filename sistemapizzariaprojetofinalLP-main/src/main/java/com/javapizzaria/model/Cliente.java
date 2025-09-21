package com.javapizzaria.model;

public class Cliente {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public Cliente(String nome, String email, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() { return nome; }
    @Override
    public String toString() { return nome + " | " + email; }
}
