package com.javapizzaria.model;

public class Gerente extends Funcionario {

    public Gerente(String nome, double salario, String turno) {
        super(nome, salario, turno);
    }

    @Override
    public String toString() {
        return "[GERENTE] " + super.toString();
    }

    // Ponto já herdado de Funcionario
}
