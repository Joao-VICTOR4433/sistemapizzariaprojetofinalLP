package com.javapizzaria.view;

import com.javapizzaria.model.Funcionario;
import com.javapizzaria.service.Administracao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteAdministracao {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Administracao admin = new Administracao(funcionarios);

        Scanner sc = new Scanner(System.in);
        int opc = -1;
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== Administração ===");
            System.out.println("1 - Listar funcionários");
            System.out.println("2 - Contratar funcionário/gerente");
            System.out.println("3 - Demitir funcionário");
            System.out.println("4 - Alterar salário/turno");
            System.out.println("5 - Registrar ponto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            if(sc.hasNextInt()){
                opc = sc.nextInt(); sc.nextLine();
            } else {
                sc.nextLine();
                opc = -1;
            }

            switch(opc){
                case 1 -> admin.listarFuncionarios();

                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Salário: ");
                    double salario = sc.nextDouble(); sc.nextLine();
                    System.out.print("Turno: ");
                    String turno = sc.nextLine();
                    System.out.print("É gerente? (s/n): ");
                    boolean isGerente = sc.nextLine().equalsIgnoreCase("s");
                    admin.contratarFuncionario(nome, salario, turno, isGerente);
                }

                case 3 -> {
                    System.out.print("ID do funcionário a demitir: ");
                    int id = sc.nextInt(); sc.nextLine();
                    admin.demitirFuncionario(id);
                }

                case 4 -> {
                    System.out.print("ID do funcionário a alterar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Novo salário: ");
                    double salario = sc.nextDouble(); sc.nextLine();
                    System.out.print("Novo turno: ");
                    String turno = sc.nextLine();
                    admin.alterarFuncionario(id, salario, turno);
                }

                case 5 -> {                    System.out.print("ID do funcionário: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Funcionario f = admin.buscarFuncionarioPorId(id);
                    if(f != null){
                        System.out.println("1 - Registrar Entrada");
                        System.out.println("2 - Registrar Saída");
                        System.out.println("3 - Listar registros");
                        System.out.print("Escolha: ");
                        int escolha = sc.nextInt(); sc.nextLine();
                        switch(escolha){
                            case 1 -> f.getPonto().registrarEntrada();
                            case 2 -> f.getPonto().registrarSaida();
                            case 3 -> f.getPonto().listarRegistros();
                            default -> System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado!");
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
