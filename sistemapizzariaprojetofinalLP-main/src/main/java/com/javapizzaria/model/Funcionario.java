package com.javapizzaria.model;

import java.time.LocalDate;

public class Funcionario {
    private static int contador = 1;
    private int id;
    private String nome;
    private String cpf;
    private String turno;
    private LocalDate dataNascimento;
    private double salario;
    private Ponto ponto;

    public Funcionario(String nome, String cpf, String turno, LocalDate dataNascimento, double salario) {
        this.id = contador++;
        this.nome = nome;
        this.cpf = cpf;
        this.turno = turno;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.ponto = new Ponto(this); // Agora precisa passar o funcionário
    }

    // === NOVO: Construtor alternativo para recuperar do relatório ===
    public Funcionario(String nome, double horasTotais) {
        this.id = contador++;
        this.nome = nome;
        this.cpf = "00000000000";
        this.turno = "Indefinido";
        this.dataNascimento = null;
        this.salario = 0.0;
        this.ponto = new Ponto(this);
        this.ponto.setHorasManuais(horasTotais);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTurno() { return turno; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public double getSalario() { return salario; }
    public Ponto getPonto() { return ponto; }

    public void setSalario(double salario) { this.salario = salario; }
    public void setTurno(String turno) { this.turno = turno; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    // === Ajustado para chamar o método existente em Ponto ===
    public double getHorasTrabalhadas() {
        return (ponto != null) ? ponto.totalHoras() : 0.0;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | CPF: " + cpf + " | Turno: " + turno + " | Salário: R$" + salario;

    }
}