package com.javapizzaria.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para controle de ponto de funcionários.
 */
public class Ponto {

    private Funcionario funcionario;
    private List<Registro> registros = new ArrayList<>();

    public Ponto(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // Marca entrada
    public void registrarEntrada() {
        registros.add(new Registro(LocalDateTime.now(), null));
        System.out.println("✅ Entrada registrada para " + funcionario.getNome() + " às " + LocalDateTime.now());
    }

    // Marca saída
    public void registrarSaida() {
        for (int i = registros.size() - 1; i >= 0; i--) {
            Registro r = registros.get(i);
            if (r.getSaida() == null) {
                r.setSaida(LocalDateTime.now());
                System.out.println("✅ Saída registrada para " + funcionario.getNome() + " às " + LocalDateTime.now());
                return;
            }
        }
        System.out.println("⚠ Nenhuma entrada pendente para " + funcionario.getNome());
    }

    // Calcula total de horas trabalhadas
    public double totalHoras() {
        double total = 0;
        for (Registro r : registros) {
            if (r.getSaida() != null) {
                Duration dur = Duration.between(r.getEntrada(), r.getSaida());
                total += dur.toMinutes() / 60.0;
            }
        }
        return total;
    }

    // Mostra registros de ponto
    public void listarRegistros() {
        System.out.println("\n📌 Registros de ponto de " + funcionario.getNome() + ":");
        for (Registro r : registros) {
            System.out.println("Entrada: " + r.getEntrada() +
                    " | Saída: " + (r.getSaida() != null ? r.getSaida() : "pendente"));
        }
        System.out.printf("Total de horas trabalhadas: %.2f horas\n", totalHoras());
    }

    // Classe interna para armazenar entrada e saída
    private static class Registro {
        private LocalDateTime entrada;
        private LocalDateTime saida;

        public Registro(LocalDateTime entrada, LocalDateTime saida) {
            this.entrada = entrada;
            this.saida = saida;
        }

        public LocalDateTime getEntrada() { return entrada; }
        public LocalDateTime getSaida() { return saida; }
        public void setSaida(LocalDateTime saida) { this.saida = saida; }
    }
}
