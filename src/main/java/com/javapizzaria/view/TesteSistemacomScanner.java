package com.javapizzaria.view;

import com.javapizzaria.model.*;
import com.javapizzaria.service.SistemaPizzaria;
import com.javapizzaria.service.Relatorio;

import java.util.List;
import java.util.Scanner;

public class TesteSistemacomScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaPizzaria sistema = new SistemaPizzaria();

        // Cadastrar produtos fixos (incluindo Rodízio)
        sistema.cadastrarComida("Pizza Calabresa", 35.0, "Pizza");
        sistema.cadastrarComida("Pizza Mussarela", 30.0, "Pizza");
        sistema.cadastrarComida("Rodízio de Pizza", 60.0, "Rodízio"); // Rodízio com valor de R$ 60
        sistema.cadastrarBebida("Coca-Cola", 6.0, 500);
        sistema.cadastrarBebida("Suco de Laranja", 5.0, 300);

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== SISTEMA PIZZARIA ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Fazer novo pedido");
            System.out.println("4 - Gerar relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opc = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine(); // limpar ENTER

            switch (opc) {
                case 1 -> cadastrarCliente(scanner, sistema);
                case 2 -> cadastrarProduto(scanner, sistema);
                case 3 -> fazerPedido(scanner, sistema);
                case 4 -> gerarRelatorio(sistema);
                case 0 -> {
                    System.out.println("Saindo...");
                    rodando = false;
                }
                default -> System.out.println("❌ Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner, SistemaPizzaria sistema) {
        System.out.println("\n=== CADASTRO DE CLIENTE ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        try {
            Cliente cliente = new Cliente(nome, email, telefone, endereco);
            sistema.cadastrarCliente(cliente);
            System.out.println("✅ Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void cadastrarProduto(Scanner scanner, SistemaPizzaria sistema) {
        System.out.println("\n=== CADASTRO DE PRODUTO ===");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // limpar ENTER

        System.out.print("Tipo (pizza/bebida/massa/sobremesa/rodízio): ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("Categoria (ex: Calabresa, Coca-Cola, etc.): ");
        String categoria = scanner.nextLine();

        try {
            switch (tipo) {
                case "pizza", "massa", "sobremesa", "rodízio" -> {
                    sistema.cadastrarComida(nome, preco, categoria);
                    System.out.println("✅ Comida cadastrada com sucesso!");
                }
                case "bebida" -> {
                    System.out.print("Volume (ml): ");
                    double volume = scanner.nextDouble();
                    scanner.nextLine(); // limpar ENTER
                    sistema.cadastrarBebida(nome, preco, volume);
                    System.out.println("✅ Bebida cadastrada com sucesso!");
                }
                default -> System.out.println("❌ Tipo de produto inválido!");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void fazerPedido(Scanner scanner, SistemaPizzaria sistema) {
        List<Cliente> clientes = sistema.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("❌ Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }

        System.out.println("\n=== FAZER PEDIDO ===");
        System.out.println("Escolha o cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " - " + clientes.get(i).getNome());
        }

        int clienteEscolhido = scanner.nextInt() - 1;
        scanner.nextLine();
        Cliente cliente = clientes.get(clienteEscolhido);

        Pedido pedido;
        try {
            pedido = sistema.novoPedido(cliente);
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            return;
        }

        // Perguntar se é rodízio
        System.out.print("Deseja rodízio? (s/n): ");
        boolean isRodizio = scanner.nextLine().equalsIgnoreCase("s");

        if (isRodizio) {
            pedido.setRodizio(true);

            // Buscar o produto Rodízio no sistema e adicionar ao pedido
            Produto rodizioProduto = null;
            for (Produto p : sistema.getProdutos()) {
                if (p.getNome().equalsIgnoreCase("Rodízio de Pizza")) {
                    rodizioProduto = p;
                    break;
                }
            }

            if (rodizioProduto != null) {
                pedido.adicionarProduto(rodizioProduto, 1);
                System.out.println("✅ Rodízio adicionado - R$ " + rodizioProduto.getPreco());
            } else {
                System.out.println("❌ Produto 'Rodízio de Pizza' não encontrado!");
            }
        }

        // Perguntar se é entrega
        System.out.print("Entrega? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Informe o endereço de entrega: ");
            pedido.setEnderecoEntrega(scanner.nextLine());
        }

        // Se NÃO for rodízio, permitir adicionar outros produtos
        if (!isRodizio) {
            boolean adicionarProdutos = true;
            while (adicionarProdutos) {
                List<Produto> produtos = sistema.getProdutos();
                if (produtos.isEmpty()) {
                    System.out.println("❌ Nenhum produto cadastrado!");
                    break;
                }

                System.out.println("\nEscolha o produto:");
                for (int i = 0; i < produtos.size(); i++) {
                    Produto p = produtos.get(i);
                    if (p instanceof Comida comida) {
                        System.out.printf("%d - %s (R$ %.2f) - %s%n",
                                i + 1, p.getNome(), p.getPreco(), comida.getTipo());
                    } else if (p instanceof Bebida bebida) {
                        System.out.printf("%d - %s (R$ %.2f) - %.0fml%n",
                                i + 1, p.getNome(), p.getPreco(), bebida.getVolume());
                    }
                }

                int produtoEscolhido = scanner.nextInt() - 1;
                scanner.nextLine();

                if (produtoEscolhido < 0 || produtoEscolhido >= produtos.size()) {
                    System.out.println("❌ Produto inválido!");
                    continue;
                }

                Produto produto = produtos.get(produtoEscolhido);

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                pedido.adicionarProduto(produto, quantidade);
                System.out.println("✅ Produto adicionado ao pedido!");

                System.out.print("Deseja adicionar outro produto? (s/n): ");
                adicionarProdutos = scanner.nextLine().equalsIgnoreCase("s");
            }
        }

        System.out.print("Informe a forma de pagamento: ");
        String pagamento = scanner.nextLine();
        pedido.finalizar(pagamento);

        try {
            sistema.registrarPedido(pedido);
            System.out.println("\n✅ PEDIDO FINALIZADO COM SUCESSO!");
            System.out.println("================================");
            System.out.println(pedido.toString());
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void gerarRelatorio(SistemaPizzaria sistema) {
        try {
            System.out.println("\n=== GERAR RELATÓRIO ===");
            String nomeArquivo = "relatorio_pedidos.txt";

            Relatorio.salvarRelatorioDiario(sistema.getPedidos(), nomeArquivo);

            System.out.println("✅ Relatório gerado com sucesso!");
            System.out.println("📄 Arquivo: " + nomeArquivo);
            System.out.println("📁 Pasta: ./relatorios/");

        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório: " + e.getMessage());
        }
    }
}