package com.javapizzaria.view;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.service.Administracao;
import com.javapizzaria.service.Relatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteAdministracao {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Administracao admin = new Administracao(funcionarios);

        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== Administração ===");
            System.out.println("1 - Listar funcionários");
            System.out.println("2 - Contratar funcionário");
            System.out.println("3 - Demitir funcionário");
            System.out.println("4 - Alterar salário/turno");
            System.out.println("5 - Registrar ponto");
            System.out.println("6 - Listar pontos de todos os funcionários");
            System.out.println("7 - Gerar relatório");
            System.out.println("8 - Recuperar funcionários do relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opc = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine(); // limpar buffer

            switch (opc) {
                case 1 -> admin.listarFuncionarios();

                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Turno: ");
                    String turno = sc.nextLine();
                    System.out.print("Salário: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();

                    admin.contratarFuncionario(nome, cpf, turno, salario, false);
                }

                case 3 -> {
                    System.out.print("ID do funcionário a demitir: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    admin.demitirFuncionario(id);
                }

                case 4 -> {
                    System.out.print("ID do funcionário a alterar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo salário: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Novo turno: ");
                    String turno = sc.nextLine();
                    admin.alterarFuncionario(id, salario, turno);
                }

                case 5 -> {
                    System.out.print("ID do funcionário: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Funcionario f = admin.buscarFuncionarioPorId(id);
                    if (f != null) {
                        System.out.println("1 - Registrar Entrada");
                        System.out.println("2 - Registrar Saída");
                        System.out.println("3 - Listar registros do ponto");
                        System.out.print("Escolha: ");
                        int escolha = sc.nextInt();
                        sc.nextLine();
                        switch (escolha) {
                            case 1 -> f.getPonto().registrarEntrada();
                            case 2 -> f.getPonto().registrarSaida();
                            case 3 -> f.getPonto().listarRegistros();
                            default -> System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado!");
                    }
                }

                case 6 -> admin.listarPontos();

                case 7 -> {
                    try {
                        String nomeArquivo = "relatorio_administracao.txt";
                        Relatorio.salvarRelatorioDiario(admin.getPedidos(), admin.getFuncionarios(), nomeArquivo);
                        System.out.println("✅ Relatório gerado com sucesso!");
                        System.out.println("📄 Arquivo: " + nomeArquivo);
                        System.out.println("📁 Pasta: ./relatorios/");
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao gerar relatório: " + e.getMessage());
                    }
                }

                case 8 -> {
                    try {
                        System.out.print("Digite o nome do relatório para recuperar funcionários: ");
                        String nomeArquivo = sc.nextLine();
                        List<Funcionario> recuperados = Relatorio.recuperarFuncionarios(nomeArquivo);

                        // adiciona ao arraylist principal
                        funcionarios.addAll(recuperados);

                        System.out.println("\n=== Funcionários recuperados do relatório ===");
                        for (Funcionario f : recuperados) {
                            System.out.println("Nome: " + f.getNome()
                                    + " | CPF: " + f.getCpf()
                                    + " | Turno: " + f.getTurno()
                                    + " | Salário: R$" + f.getSalario()
                                    + " | Total horas: " + f.getHorasTrabalhadas());
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao recuperar funcionários: " + e.getMessage());
                    }
                }

                case 0 -> {
                    System.out.println("Saindo...");
                    rodando = false;
                }

                default -> System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
