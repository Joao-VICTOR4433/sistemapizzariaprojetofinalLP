// Cliente.java
package com.javapizzaria.model;

public class Cliente extends Usuario {
    private String email;
    private String telefone;
    private String endereco;

    public Cliente(String nome, String email, String telefone, String endereco) {
        super(nome);
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
