package com.javapizzaria.service;

import com.javapizzaria.model.Pedido;

import java.io.*;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Relatorio {
    private static final String PASTA_RELATORIOS = "./relatorios/";

    // Salvar relatório em TXT
    public static File salvarRelatorioDiario(List<Pedido> pedidos, String nomeArquivo) throws IOException {
        File pasta = new File(PASTA_RELATORIOS);
        if (!pasta.exists()) pasta.mkdirs();

        // Garante extensão .txt
        if (!nomeArquivo.endsWith(".txt")) {
            nomeArquivo += ".txt";
        }

        File arquivo = new File(pasta, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("=== RELATÓRIO DE PEDIDOS ===");
            bw.newLine();
            bw.write("Data de geração: " +
                    java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            bw.newLine();
            bw.newLine();

            if (pedidos.isEmpty()) {
                bw.write("Nenhum pedido registrado.");
            } else {
                for (Pedido p : pedidos) {
                    bw.write(p.toString());
                    bw.newLine();
                    bw.write("------------------------------");
                    bw.newLine();
                }
            }
        }
        return arquivo;
    }

    // Listar relatórios salvos
    public static List<File> listarRelatorios() {
        File pasta = new File(PASTA_RELATORIOS);
        if (!pasta.exists()) return new ArrayList<>();
        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".txt"));
        return arquivos != null ? Arrays.asList(arquivos) : new ArrayList<>();
    }

    // Visualizar conteúdo de um relatório
    public static String visualizarRelatorio(int index) throws IOException {
        List<File> arquivos = listarRelatorios();
        if (index < 0 || index >= arquivos.size()) return "Relatório não encontrado.";
        return Files.readString(arquivos.get(index).toPath());
    }
}
