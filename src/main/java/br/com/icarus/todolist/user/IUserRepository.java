package br.com.icarus.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByUsername(String username);

}


// Interface - Modelo dentro da nossa aplicação (Implementação dos métodos)
// <> - Classe recebe um generator (atributos dinâmicos)