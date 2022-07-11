package br.com.zup.lms.compartilhado.seguranca;

import org.springframework.context.annotation.Profile;

import java.util.Map;

@Profile("dev")
/**
 * Classe criada só para ser usada em dev para simular um objeto com o método getClaims() que é o
 * esperado para ser usado nos pontos de usuário autenticado.
 *
 * @author albertoluizsouza
 */
public class UsuarioLogadoParaDev {

  private String email;

  public UsuarioLogadoParaDev(String email) {
    this.email = email;
  }

  public Map<String, String> getClaims() {
    return Map.of("email", email);
  }
}
