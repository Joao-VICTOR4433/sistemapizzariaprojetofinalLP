package com.javapizzaria.service;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.model.Gerente;

import java.util.List;

public class Administracao {
    private List<Funcionario> funcionarios;

    public Administracao(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    // Contratar funcionário ou gerente
    public void contratarFuncionario(String nome, double salario, String turno, boolean isGerente) {
        if (isGerente) {
            funcionarios.add(new Gerente(nome, salario, turno));
        } else {
            funcionarios.add(new Funcionario(nome, salario, turno));
        }
        System.out.println("✅ Funcionário contratado: " + nome);
    }

    // Demitir funcionário
    public void demitirFuncionario(int id) {
        Funcionario f = buscarFuncionarioPorId(id);
        if (f != null) {
            funcionarios.remove(f);
            System.out.println("❌ Funcionário demitido: " + f.getNome());
        } else {
            System.out.println("⚠ Funcionário não encontrado!");
        }
    }

    // Alterar salário e turno
    public void alterarFuncionario(int id, double novoSalario, String novoTurno) {
        Funcionario f = buscarFuncionarioPorId(id);
        if (f != null) {
            f.setSalario(novoSalario);
            f.setTurno(novoTurno);
            System.out.println("✅ Funcionário atualizado: " + f);
        } else {
            System.out.println("⚠ Funcionário não encontrado!");
        }
    }

    // Buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    // Listar todos funcionários
    public void listarFuncionarios() {
        System.out.println("\n👥 Funcionários:");
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
    }
}
