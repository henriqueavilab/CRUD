package debug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("Bem vindo ao cadastro de usuarios.");

        while (true) {
            displayMenu(); // Exibe o menu
            int userInput = getMenuInput(); // Obtém a entrada do usuário de forma segura

            if (userInput == -1) { // -1 indica entrada inválida
                continue;
            }
            
            // Verifica se a opção é válida (entre 1 e o número de opções do enum)
            if (userInput < 1 || userInput > MenuOption.values().length) {
                System.out.println("Opção inválida. Por favor, selecione um número de 1 a " + MenuOption.values().length + ".");
                continue;
            }

            var selectedOption = MenuOption.values()[userInput - 1];

            switch (selectedOption) {
                case SAVE -> dao.save(requestToSave());
                case UPDATE -> {
                    UserModel userToUpdate = requestToUpdate();
                    if (userToUpdate != null) {
                        dao.update(userToUpdate);
                    }
                }
                case DELETE -> dao.delete(requestId("excluir"));
                case FIND_BY_ID -> dao.findById(requestId("buscar"));
                case LIST -> dao.list();
                case EXIT -> {
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close(); // Fechar o scanner ao sair
                    return; // Sai do método main, encerrando o programa
                }
                // Não é necessário um 'default' aqui se o tratamento de userInput já cobre todas as opções
            }
        }
    }

    // Método para exibir o menu
    private static void displayMenu() {
        System.out.println("\nSelecione a operacao desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Buscar por identificador");
        System.out.println("5 - Listar");
        System.out.println("6 - Sair");
    }

    // Método para obter e validar a entrada do menu
    private static int getMenuInput() {
        int input = -1;
        try {
            input = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar a entrada inválida
        }
        return input;
    }

    // Solicita um ID ao usuário para ações como exclusão ou busca
    private static long requestId(String action) {
        System.out.println("Informe o ID do usuário para " + action + ":");
        long id = -1;
        while (true) {
            try {
                id = scanner.nextLong();
                scanner.nextLine(); // Consumir a quebra de linha
                break; // Sai do loop se o ID for válido
            } catch (InputMismatchException e) {
                System.out.println("ID inválido. Por favor, digite um número inteiro:");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
        return id;
    }

    // Solicita os dados para criar um novo usuário
    private static UserModel requestToSave() {
        System.out.println("--- Cadastro de Novo Usuário ---");
        System.out.println("Informe o nome do usuario:");
        var name = scanner.nextLine();
        System.out.println("Informe o e-mail do usuario:");
        var email = scanner.nextLine();

        LocalDate birthday = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Informe a data de nascimento do usuario: (dd/MM/yyyy)");
            var birthdayString = scanner.nextLine();
            try {
                birthday = LocalDate.parse(birthdayString, dateFormatter);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, use dd/MM/yyyy. Ex: 15/03/1990");
            }
        }
        // ID 0 é um placeholder; o DAO atribuirá um ID real
        return new UserModel(0, name, email, birthday);
    }

    // Solicita os dados para atualizar um usuário existente
    private static UserModel requestToUpdate() {
        System.out.println("--- Atualização de Usuário ---");
        long id = requestId("atualizar"); // Primeiro, peça o ID do usuário a ser atualizado

        // Opcional: Buscar o usuário existente para pré-preencher ou validar
        UserModel existingUser = dao.findById(id); // Usa o DAO para buscar o usuário
        if (existingUser == null) {
            System.out.println("Não foi possível encontrar o usuário para atualização.");
            return null; // Não encontrou o usuário, não pode atualizar
        }

        System.out.println("Informe o novo nome do usuario (ou ENTER para manter o atual: '" + existingUser.getName() + "'):");
        String nameInput = scanner.nextLine();
        String name = nameInput.isEmpty() ? existingUser.getName() : nameInput;

        System.out.println("Informe o novo e-mail do usuario (ou ENTER para manter o atual: '" + existingUser.getEmail() + "'):");
        String emailInput = scanner.nextLine();
        String email = emailInput.isEmpty() ? existingUser.getEmail() : emailInput;

        LocalDate birthday = existingUser.getBirthday();
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Informe a nova data de nascimento do usuario (dd/MM/yyyy) (ou ENTER para manter a atual: '" + existingUser.getBirthday().format(dateFormatter) + "'):");
            String birthdayString = scanner.nextLine();
            if (birthdayString.isEmpty()) {
                validDate = true; // Mantém a data existente se a entrada for vazia
            } else {
                try {
                    birthday = LocalDate.parse(birthdayString, dateFormatter);
                    validDate = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido. Por favor, use dd/MM/yyyy. Ex: 15/03/1990");
                }
            }
        }
        // Retorna um novo UserModel com os dados atualizados e o ID original
        return new UserModel(id, name, email, birthday);
    }
}