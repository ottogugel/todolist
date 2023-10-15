package br.com.icarus.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Tipo de Modificador
 * public - qualquer um pode acessar
 * private - restrição, somente atributos
 * protected - mesma estrutura do pacote
 * Durante a criação da classe precisamos saber qual vai ser o escopo do método como (Public) e qual vai ser o tipo de retorno (String, Integer)
 */

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create (@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());

    if(user != null ) {
      // Mensagem de erro
      // Status Code
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
    }

    // Criptografia da senha
    var passwordHashred = BCrypt.withDefaults().
    hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashred);

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userCreated);
  }

}

/**
   * String (texto)
   * Inter (inteiro) Números inteiros
   * Double (double) Números com casas decimais (0.0000)
   * Float (float) Número de caracteres (0.000)
   * Char (A C) Caracteres
   * Date (data) Datas
   * Void quando não temos um retorno, apenas uma lógica seja executada.
   */

   /**
    * Receber o objeto no Body (Corpo da requisição)
    * != diferente
    */