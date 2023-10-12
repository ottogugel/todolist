package br.com.icarus.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // Criar páginas, templates
@RestController // Construindo uma API que vai retornar o conceito de rest, restful para entregar uma API.

@RequestMapping("/primeiraRota") // Estruturar a rota
//http://localhost:8080/primeiraRota -- A rota
public class MinhaPrimeiraController {

  /**
   * Métodos de acesso ao HTTP -> Requisição
   * GET - Buscar uma informação
   * POST - Adicionar um dado/informação
   * PUT - Alterar uma dado/info
   * DELETE - Remover um dado
   * PATCH - Alterar somente uma parte da info/dado
  */

  // Método (Funcionalidade) de uma classe
  @GetMapping("/primeiroMetodo")
  public String primeiraMensagem() {
    return "Funcionou";
  }
}
