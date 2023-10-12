package br.com.icarus.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    */
  @PostMapping("/")
  public void create (@RequestBody UserModel userModel) {
    System.out.println(userModel.getUsername());
  }

}
