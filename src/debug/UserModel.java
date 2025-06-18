package debug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Importar para o toString formatar a data

// Classe de modelo para o usuário (User Model)
public class UserModel {
    private long id;
    private String name;
    private String email;
    private LocalDate birthday; // Usando LocalDate para data de nascimento sem fuso horário

    public UserModel(long id, String name, String email, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    // Getters para acessar os atributos
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    // Setters (opcional, mas comum para atualização de campos específicos)
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        // Formata a data para exibir no formato dd/MM/yyyy no toString
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID: " + id + ", Nome: " + name + ", Email: " + email + ", Aniversário: " + birthday.format(formatter);
    }
}