package com.javapizzaria.model;

import java.time.LocalDate;

public class Gerente extends Funcionario {
    public Gerente(String nome, String cpf, String turno, LocalDate dataNascimento, double salario) {
        super(nome, cpf, turno, dataNascimento, salario);
    }

    @Override
    public String toString() {
        return "GERENTE - " + super.toString();
    }
}
