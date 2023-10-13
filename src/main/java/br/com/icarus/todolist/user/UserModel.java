package br.com.icarus.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@Entity(name= "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(unique = true) // Validação para não enviar os mesmos dados p/ DBA.
  private String username;
  private String name;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

}

/**
 * Métodos Getters and Setters - São usados para proteger os dados especialmente
 na criação de classes. Para cada instância de variávél, um método getter retorna seu valor, enquanto o método setter define ou atualiza.
 */