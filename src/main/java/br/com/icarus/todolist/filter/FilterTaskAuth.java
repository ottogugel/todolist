package br.com.icarus.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.icarus.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Validando a rota
    var servletPath = request.getServletPath();

    if (servletPath.equals("/tasks/")) {

      // Pegar a autenticação (usuario e senha)
      var authorization = request.getHeader("Authorization");

      var authEncoded = authorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecode); // Converter o array de bytes para um string

      // ["Merce123", "Merce123"]
      String[] credentials = authString.split(":"); // Dividir os dois pontos
      String username = credentials[0]; // Divisão do usuário
      String password = credentials[1]; // Divisão da senha
      System.out.println(username);
      System.out.println(password);
      // Validar usuario
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401);
      } else {
        // Validar a senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (passwordVerify.verified) {
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }

      }

    } else {
        filterChain.doFilter(request, response);
    }

  }

}

// Serve para autenticar o usuário antes de permitir o cadastro de uma tarefa.
// Para que a classe seja gerenciada no Spring utiliza-se o
// @component/@restcontroller
// Substring serve para extrair a parte de um conteudo
// trim (remove os espaços que a palavra tem)