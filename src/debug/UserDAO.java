package debug;

import java.util.ArrayList;
import java.util.List;

// Classe de acesso a dados (User DAO - Data Access Object)
// Para este exemplo, apenas simula as operações em memória
public class UserDAO {
    private List<UserModel> users = new ArrayList<>();
    private long nextId = 1; // Para simular IDs únicos para novos usuários

    public void save(UserModel user) {
        if (user != null) {
            // Cria um novo UserModel para garantir que o ID é atribuído pelo DAO
            UserModel newUser = new UserModel(nextId++, user.getName(), user.getEmail(), user.getBirthday());
            users.add(newUser);
            System.out.println("Usuário salvo com sucesso: " + newUser);
        } else {
            System.out.println("Não foi possível salvar o usuário. Dados inválidos.");
        }
    }

    public void update(UserModel user) {
        if (user == null) {
            System.out.println("Dados de usuário inválidos para atualização.");
            return;
        }
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                // Atualiza o usuário existente na lista
                users.set(i, user);
                System.out.println("Usuário atualizado com sucesso: " + user);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Usuário com ID " + user.getId() + " não encontrado para atualização.");
        }
    }

    public void delete(long id) {
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (removed) {
            System.out.println("Usuário com ID " + id + " excluído com sucesso.");
        } else {
            System.out.println("Usuário com ID " + id + " não encontrado para exclusão.");
        }
    }

    public UserModel findById(long id) {
        for (UserModel user : users) {
            if (user.getId() == id) {
                System.out.println("Usuário encontrado: " + user);
                return user;
            }
        }
        System.out.println("Usuário com ID " + id + " não encontrado.");
        return null;
    }

    public void list() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Lista de usuários:");
            for (UserModel user : users) {
                System.out.println("- " + user);
            }
        }
    }
}