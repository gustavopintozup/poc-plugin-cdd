package br.com.zup.lms.compartilhado.seguranca;

import br.com.zup.lms.alunos.AlunoRepository;
import br.com.zup.lms.compartilhado.infra.ICP;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

@ICP(4)
public class LocalAuthenticationFilter extends OncePerRequestFilter {

  // 1
  private final AlunoRepository alunoRepository;

  public LocalAuthenticationFilter(AlunoRepository alunoRepository) {
    this.alunoRepository = alunoRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    UsernamePasswordAuthenticationToken alunoAutenticado;

    var novoAlunoHeader = request.getHeader("novo-aluno");
    // 1
    if (novoAlunoHeader != null) {
      // provavelmente pode ser qq coisa aqui que tenha claims e o email dentro
      var jwt =
          new Jwt(
              "token-value", null, null, Map.of("alg", "HA-256"), Map.of("email", novoAlunoHeader));
      alunoAutenticado = new UsernamePasswordAuthenticationToken(jwt, null, null);
      // 1
    } else {

      var idEstudanteTexto = request.getHeader("Authorization");

      // estou deixando como assert pq isso rola em dev e a pessoa dev
      // deveria passar isso.
      Assert.hasText(
          idEstudanteTexto, "É necessário o header Authorization para a chamada autenticada");
      Assert.isTrue(
          idEstudanteTexto.startsWith("Bearer "),
          "O header de autorizacao tem que começar com Bearer ");
      var idEstudante = Long.valueOf(idEstudanteTexto.split(" ")[1]);

      // 1
      var estudante =
          alunoRepository
              .findById(idEstudante)
              .orElseThrow(
                  () ->
                      new IllegalStateException(
                          "Provavelmente o aluno de id "
                              + idEstudante
                              + " ainda não foi registrado no sistema. Já chamou o endpoint que faz"
                              + " o registro?"));

      alunoAutenticado =
          new UsernamePasswordAuthenticationToken(
              new UsuarioLogadoParaDev(estudante.getEmail()), null, null);
    }

    SecurityContextHolder.getContext().setAuthentication(alunoAutenticado);

    filterChain.doFilter(request, response);
  }
}
