package br.com.icarus.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID>{
  List<TaskModel> findByIdUser(UUID idUser);
}


// Toda vez que for criado uma interface lembrar de usar o extends e passar as entidades/ids.