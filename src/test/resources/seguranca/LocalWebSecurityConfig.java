package br.com.zup.lms.compartilhado.seguranca;

import br.com.zup.lms.alunos.AlunoRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Sim, essa lógica no @Profile está redundante. Quis deixar explicíto que o bean não deve ser carregado quando estiver
 * em prod
 */
@Profile({"dev", "!prod", "!test", "!keycloak"})
/*
 * essa anotação aqui teoricamente é desnecessária, já que o profile de dev nunca deveria estar habilitado em prod,
 * mas como essa classe poderia abrir uma brecha de segurança grande se for carregada em prod, deixei o conditional
 * como uma garantia a mais
 */
@ConditionalOnMissingBean(WebSecurityConfig.class)
@EnableWebSecurity
public class LocalWebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AlunoRepository estudanteRepository;

  public LocalWebSecurityConfig(AlunoRepository estudanteRepository) {
    this.estudanteRepository = estudanteRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .cors()
        .and()
        .httpBasic()
        .disable()
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .logout()
        .disable()
        .addFilterBefore(
            new LocalAuthenticationFilter(estudanteRepository),
            UsernamePasswordAuthenticationFilter.class);
    // @formatter:on
  }
}
