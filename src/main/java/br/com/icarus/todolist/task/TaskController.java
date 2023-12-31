package br.com.icarus.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarus.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    System.out.println("Chegou no controller");
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);

    // Validação da hora
    var currentDate = LocalDateTime.now();
    if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt()) ) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término deve ser maior do que a data atual.");
    }
  // Validação da hora
    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término.");
    }


    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }
  // Listar as tarefas do usuário
  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
  }

  // Atualizar a tarefa
  // localhost:8080/tasks/892321321-aasdadsa-12312312
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
    var task = this.taskRepository.findById(id).orElse(null);

    // Se a tarefa não for existente.
    if(task == null ) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada.");
    }

    // Se ela existir mas se o ID do usuário for diferente não irá permitir.
    var idUser = request.getAttribute("idUser");

    if(!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa.");
    }

    Utils.copyNonNullProperties(taskModel, task);
    var taskUpdated = this.taskRepository.save(task);
    return ResponseEntity.ok().body(taskUpdated);
  }

}
