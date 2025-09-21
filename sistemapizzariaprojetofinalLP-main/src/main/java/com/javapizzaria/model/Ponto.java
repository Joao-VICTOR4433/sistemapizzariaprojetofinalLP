package com.javapizzaria.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ponto {
    private final Funcionario funcionario;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double horasTrabalhadas;
    private final List<String> registros; // Lista de registros de ponto

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Ponto(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.registros = new ArrayList<>();
    }

    public void registrarEntrada() {
        entrada = LocalDateTime.now();
        registros.add("Entrada: " + entrada.format(formatter));
        System.out.println("Entrada registrada: " + entrada.format(formatter));
    }

    public void registrarSaida() {
        if (entrada == null) {
            System.out.println("Entrada não registrada!");
            return;
        }
        saida = LocalDateTime.now();
        double horasDia = Duration.between(entrada, saida).toMinutes() / 60.0;
        horasTrabalhadas += horasDia;
        registros.add("Saída: " + saida.format(formatter) + " | Horas trabalhadas no dia: " + String.format("%.2f", horasDia));
        System.out.println("Saída registrada: " + saida.format(formatter) + " | Horas trabalhadas no dia: " + String.format("%.2f", horasDia));
        entrada = null;
        saida = null;
    }

    public double totalHoras() {
        return horasTrabalhadas;
    }

    public void listarRegistros() {
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro de ponto encontrado.");
            return;
        }
        System.out.println("=== Registros de Ponto do funcionário " + funcionario.getNome() + " ===");
        for (String r : registros) {
            System.out.println(r);
        }
    }
}
