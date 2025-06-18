# Simulador de Banco no Console

Este é um projeto simples em Java que simula a criação de uma conta bancária por meio de interações no console. O programa permite que o usuário escolha um banco, cadastre informações pessoais, gere um número de conta aleatório e realize um depósito inicial.

## Estrutura do Projeto


- **`src/`**: Contém os arquivos-fonte Java.
  - **`debug/`**: Contem todos os arquivos das classes.
    - **`Main.java`**: É o ponto de entrada da aplicação, gerenciando o menu de interação com o usuário para realizar operações CRUD (Criar, Ler, Atualizar, Deletar) de usuários através do **`UserDAO.java`**.
    - **`MenuOption.java`**: Define as opções de menu disponíveis para a aplicação usando um enum, tornando o código mais legível e seguro.
    - **`UserDAO.java`**: Simula as operações de salvar, atualizar, deletar, encontrar e listar dados de usuários em memória, agindo como uma camada de acesso aos dados.
    - **`UserModel.java`**: Define a estrutura de dados para um usuário, incluindo ID, nome, e-mail e data de nascimento, com métodos para acessar e modificar esses dados.

## Funcionalidades

1. Cadastro de Usuários: Permite adicionar novos usuários com nome, e-mail e data de nascimento.
2. Atualização de Usuários: Possibilita modificar os dados (nome, e-mail, data de nascimento) de um usuário existente.
3. Exclusão de Usuários: Permite remover um usuário do sistema pelo seu ID.
4. Busca de Usuários: Encontra e exibe os detalhes de um usuário específico utilizando seu ID.
5. Listagem de Usuários: Exibe todos os usuários atualmente cadastrados no sistema.
6. Menu Interativo: Oferece uma interface de console para navegar e executar as operações CRUD de forma amigável.
7. Validação de Entrada: Garante que dados como IDs e datas sejam inseridos no formato correto.
8. Gerenciamento de Dados em Memória: Simula um banco de dados para armazenar e manipular as informações dos usuários.

## Pré-requisitos

- **Java Development Kit (JDK)** instalado (versão 8 ou superior).
- Um editor de texto ou IDE, como **Visual Studio Code**.

## Como Rodar o Projeto

1. **Clone o repositório** (se necessário):
   ```sh
   javac -d bin src/*.java

   java -cp bin ContaTerminal
