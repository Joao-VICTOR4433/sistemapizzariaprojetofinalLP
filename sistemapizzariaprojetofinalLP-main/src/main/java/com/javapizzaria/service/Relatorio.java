package com.javapizzaria.service;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.model.Pedido;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Relatorio {

    private static final String PASTA_RELATORIOS = "./relatorios/";

    public static File salvarRelatorioDiario(List<Pedido> pedidos,
                                             List<Funcionario> funcionarios,
                                             String nomeArquivo) throws IOException {
        File pasta = new File(PASTA_RELATORIOS);
        if (!pasta.exists()) pasta.mkdirs();

        if (!nomeArquivo.endsWith(".txt")) {
            nomeArquivo += ".txt";
        }

        File arquivo = new File(pasta, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("=== RELATÓRIO DIÁRIO ===\n");
            bw.write("Data de geração: " +
                    java.time.LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n\n");

            bw.write("=== PEDIDOS ===\n");
            if (pedidos == null || pedidos.isEmpty()) {
                bw.write("Nenhum pedido registrado.\n");
            } else {
                for (Pedido p : pedidos) {
                    bw.write(p.toString() + "\n");
                    bw.write("------------------------------\n");
                }
            }

            bw.write("\n=== REGISTROS DE PONTO ===\n");
            if (funcionarios == null || funcionarios.isEmpty()) {
                bw.write("Nenhum funcionário registrado.\n");
            } else {
                for (Funcionario f : funcionarios) {
                    bw.write("Funcionário: " + f.getNome() +
                            " - Total horas: " + String.format("%.2f", f.getPonto().totalHoras()) + "\n");
                }
            }
        }
        return arquivo;
    }
}
