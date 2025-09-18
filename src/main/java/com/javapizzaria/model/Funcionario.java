package com.javapizzaria.model;

public class Funcionario extends Usuario {
    private double salario;
    private String turno;
    private Ponto ponto; 

    public Funcionario(String nome, double salario, String turno) {
        super(nome);
        this.salario = salario;
        this.turno = turno;
        this.ponto = new Ponto(this); 
    }

    
    public double getSalario() { return salario; }
    public String getTurno() { return turno; }
    public Ponto getPonto() { return ponto; } 

    
    public void setSalario(double salario) { this.salario = salario; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Nome: " + getNome() + " | Salário: R$" + salario + " | Turno: " + turno;
    }
}
