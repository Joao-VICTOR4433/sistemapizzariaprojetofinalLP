package com.javapizzaria.service;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.model.Gerente;
import com.javapizzaria.model.Pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administracao {
    private final List<Funcionario> funcionarios;
    private final List<Pedido> pedidos;

    public Administracao(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        this.pedidos = new ArrayList<>();
    }

    public void contratarFuncionario(String nome, String cpf, String turno, double salario, boolean isGerente) {
        Funcionario f = isGerente ? new Gerente(nome, cpf, turno, LocalDate.now(), salario)
                : new Funcionario(nome, cpf, turno, LocalDate.now(), salario);
        funcionarios.add(f);
        System.out.println("✅ Funcionário contratado: " + f);
    }

    public void demitirFuncionario(int id) {
        Funcionario f = buscarFuncionarioPorId(id);
        if(f != null) {
            funcionarios.remove(f);
            System.out.println("✅ Funcionário demitido: " + f.getNome());
        } else System.out.println("❌ Funcionário não encontrado!");
    }

    public void alterarFuncionario(int id, double salario, String turno) {
        Funcionario f = buscarFuncionarioPorId(id);
        if(f != null){
            f.setSalario(salario);
            f.setTurno(turno);
            System.out.println("✅ Alteração realizada: " + f);
        } else System.out.println("❌ Funcionário não encontrado!");
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        if(funcionarios.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
        else funcionarios.forEach(f -> System.out.println(f));
    }

    public void listarPontos() {
        System.out.println("\n=== PONTOS DE FUNCIONÁRIOS ===");
        if(funcionarios.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
        else {
            for(Funcionario f : funcionarios){
                System.out.printf("[%d] %s - Horas trabalhadas: %.2f%n",
                        f.getId(), f.getNome(), f.getPonto().totalHoras());
            }
        }
    }

    public List<Funcionario> getFuncionarios() { return funcionarios; }

    public List<Pedido> getPedidos() { return pedidos; }
}
