# 🍕 Sistema de Pizzaria - Projeto Final LP

![Java](https://img.shields.io/badge/Java-17-orange?logo=java\&logoColor=white) ![Status](https://img.shields.io/badge/status-completo-brightgreen)

Um **sistema de gerenciamento de pizzaria** desenvolvido em Java, com foco em **controle de funcionários, clientes, produtos, pedidos e relatórios**.

---

## 📋 Funcionalidades

### **Administração de Funcionários**

* ✅ Listar funcionários
* ✅ Contratar funcionários ou gerentes
* ✅ Demitir funcionários
* ✅ Alterar salário e turno
* ✅ Registrar ponto (entrada e saída)
* ✅ Visualizar horas trabalhadas
* ✅ Gerar relatório completo de funcionários

### **Gestão de Clientes**

* ✅ Cadastrar clientes com dados completos (nome, e-mail, telefone, endereço)

### **Produtos**

* ✅ Cadastrar comidas (pizza, massa, rodízio, sobremesa)
* ✅ Cadastrar bebidas (com volume em ml)
* ✅ Listar produtos disponíveis

### **Pedidos**

* ✅ Criar novo pedido para um cliente
* ✅ Adicionar produtos e quantidades
* ✅ Escolher entrega ou retirada
* ✅ Opção de rodízio
* ✅ Finalizar pedido com forma de pagamento
* ✅ Visualizar pedido finalizado

### **Relatórios**

* ✅ Relatório diário de pedidos
* ✅ Relatório de funcionários com total de horas trabalhadas

---

## 🛠 Estrutura do Projeto

```
src/
├─ main/
│  ├─ java/
│  │  ├─ com.javapizzaria.model/
│  │  │   ├─ Funcionario.java
│  │  │   ├─ Gerente.java
│  │  │   ├─ Cliente.java
│  │  │   ├─ Produto.java
│  │  │   ├─ Comida.java
│  │  │   ├─ Bebida.java
│  │  │   ├─ Pedido.java
│  │  │   ├─ Ponto.java
│  │  ├─ com.javapizzaria.service/
│  │  │   ├─ Administracao.java
│  │  │   ├─ SistemaPizzaria.java
│  │  │   ├─ Relatorio.java
│  │  ├─ com.javapizzaria.view/
│  │      ├─ TesteAdministracao.java
│  │      ├─ TesteSistemacomScanner.java
```

---

## ⚙️ Como Rodar

### Pré-requisitos

* **Java 17 ou superior**
* Terminal ou IDE compatível (IntelliJ, Eclipse, VS Code)



## 💡 Tecnologias e Conceitos

* Java 17
* POO: Herança, Polimorfismo, Encapsulamento
* Collections: `List`, `Map`
* Datas e horários: `LocalDate`, `LocalDateTime`
* Entrada de dados via `Scanner`
* Arquivos: geração de relatórios em `.txt`

---

## 🎯 Observações

* Sistema **console-based**, mas extensível para interface gráfica
* Todos os dados são **em memória**, exceto relatórios salvos
* Funcionalidades completas de administração, vendas e relatórios

---

