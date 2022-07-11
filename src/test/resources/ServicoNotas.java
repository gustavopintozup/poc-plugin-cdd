package br.com.zup.lms.alunos;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.zup.lms.admin.partials.learningtask.LearningTask;
import br.com.zup.lms.compartilhado.HandoraLogBuilder;
import br.com.zup.lms.compartilhado.infra.ICP;
import feign.FeignException;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Serve como ponte para consultar a última nota relativa a uma {@link LearningTask}
 *
 * @author albertoluizsouza
 */
@Service
@ICP(7.5)
public class ServicoNotas {

  // 1
  private final ServicoNotasRemoto servicoNotasRemoto;

  @ICP(0.5)
  private static final Logger log = LoggerFactory.getLogger(ServicoNotas.class);

  public ServicoNotas(ServicoNotasRemoto servicoNotasRemoto) {
    this.servicoNotasRemoto = servicoNotasRemoto;
  }

  /**
   * @param learningTaskCorrente
   * @param aluno
   * @return a possível {@link Nota} da pessoa aluna para aquela {@link LearningTask}
   */
  // 3
  public Optional<ServicoNotasResponse> consulta(LearningTask learningTaskCorrente, Aluno aluno) {

    Assert.isTrue(
        Objects.nonNull(learningTaskCorrente.getId()),
        "Para consultar uma nota a learning task precisa ter um id associado");
    // 1
    try {
      // #log saída do sistema
      HandoraLogBuilder.metodo()
          .oQueEstaAcontecendo("Logo antes de consultar a nota no serviço de notas")
          .adicionaInformacao(
              "linkSubmissaoResposta", learningTaskCorrente.getLinkSubmissaoResposta())
          .adicionaInformacao(
              "codigo", learningTaskCorrente.calculaCodigoIdentificadorResposta(aluno))
          .adicionaInformacao("id-learning-task", learningTaskCorrente.getId().toString())
          .info(log);

      ServicoNotasResponse resposta =
          servicoNotasRemoto.consulta(
              learningTaskCorrente.getLinkSubmissaoResposta(),
              learningTaskCorrente.calculaCodigoIdentificadorResposta(aluno));

      HandoraLogBuilder.metodo()
          .oQueEstaAcontecendo("Logo depois de consultar a nota no serviço de notas")
          .adicionaInformacao("resposta", resposta.toString())
          .info(log);

      return Optional.of(resposta);
      // 1
    } catch (FeignException e) {
      // 1
      if (e.status() == NOT_FOUND.value()) {
        HandoraLogBuilder.metodo()
            .oQueEstaAcontecendo("Aparentemente a nota não estava disponível")
            .adicionaInformacao("status", e.status() + "")
            .erro(log, e);

        return Optional.empty();
      }

      throw new RuntimeException(
          HandoraLogBuilder.metodo()
              .oQueEstaAcontecendo("Aconteceu um problema inesperado")
              .adicionaInformacao("status", e.status() + "")
              .adicionaInformacao("feign-mensagem-exception", e.getMessage())
              .mensagem(),
          e);
    }
  }
}
