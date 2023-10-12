package br.com.icarus.todolist.user;

import lombok.Data;

@Data
public class UserModel {
  private String username;
  private String name;
  private String password;


}

/**
 * Métodos Getters and Setters - São usados para proteger os dados especialmente
 na criação de classes. Para cada instância de variávél, um método getter retorna seu valor, enquanto o método setter define ou atualiza.
 */