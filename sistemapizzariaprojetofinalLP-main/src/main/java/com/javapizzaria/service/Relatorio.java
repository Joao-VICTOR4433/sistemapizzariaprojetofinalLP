package com.javapizzaria.service;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.model.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Relatorio {

    private static final String PASTA_RELATORIOS = "./relatorios/";

    public static void salvarRelatorioDiario(List<Pedido> pedidos, List<Funcionario> funcionarios, String nomeArquivo) throws IOException {
        File pasta = new File(PASTA_RELATORIOS);
        if (!pasta.exists()) {
            pasta.mkdirs(); // cria a pasta se não existir
        }

        File arquivo = new File(pasta, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("=== RELATÓRIO DE FUNCIONÁRIOS ===");
            writer.newLine();
            for (Funcionario f : funcionarios) {
                writer.write("Funcionário: " + f.getNome()
                        + " | CPF: " + f.getCpf()
                        + " | Turno: " + f.getTurno()
                        + " | Salário: " + f.getSalario()
                        + " | Total horas: " + String.format("%.2f", f.getPonto().totalHoras()));
                writer.newLine();
            }

            writer.newLine();
            writer.write("=== RELATÓRIO DE PEDIDOS ===");
            writer.newLine();
            for (Pedido p : pedidos) {
                writer.write(p.toString());
                writer.newLine();
            }
        }
    }

    public static List<Funcionario> recuperarFuncionarios(String nomeArquivo) throws IOException {
        File arquivo = new File(PASTA_RELATORIOS, nomeArquivo);
        if (!arquivo.exists()) {
            throw new IOException("Arquivo não encontrado: " + arquivo.getAbsolutePath());
        }

        List<Funcionario> funcionarios = new ArrayList<>();
        try (Scanner sc = new Scanner(arquivo)) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.startsWith("Funcionário:")) {
                    String[] partes = linha.split("\\|");

                    String nome = partes[0].replace("Funcionário:", "").trim();
                    String cpf = partes[1].replace("CPF:", "").trim();
                    String turno = partes[2].replace("Turno:", "").trim();
                    double salario = Double.parseDouble(partes[3].replace("Salário:", "").trim().replace(",", "."));

                    String horasStr = partes[4].replace("Total horas:", "").trim();

                    Funcionario f = new Funcionario(nome, 0.0);
                    f.setTurno(turno);
                    f.setSalario(salario);
                    f.setCpf(cpf);


                    funcionarios.add(f);

                    System.out.println("⚠️ Observação: " + nome + " tinha " + horasStr + " horas no relatório.");
                }
            }
        }

        return funcionarios;
    }
}
