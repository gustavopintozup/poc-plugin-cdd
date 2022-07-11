package br.com.zup.lms.alunos;

import br.com.zup.lms.compartilhado.infra.ICP;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação de cache trivial para guardar o retorno das respostas de determinado aluno durante
 * um fluxo de execução.
 *
 * @author albertoluizsouza
 */
@ICP(2)
public class CacheRespostasDeDeterminadoAluno implements RespostasDeterminadoAluno {

  // 2
  private RespostasDeterminadoAluno respostasDeterminadoAluno;
  private Map<String, Collection<RespostaLearningTask>> cacheRespostasTaskClass = new HashMap<>();
  private Map<String, Collection<RespostaLearningTask>> cacheRespostasLearningTask =
      new HashMap<>();
  private Map<String, Optional<RespostaLearningTask>> cacheUltimasRespostasLearningTask =
      new HashMap<>();

  public CacheRespostasDeDeterminadoAluno(RespostasDeterminadoAluno respostasDeterminadoAluno) {
    super();
    this.respostasDeterminadoAluno = respostasDeterminadoAluno;
  }

  @Override
  public Collection<RespostaLearningTask> buscaRespostas(Long idAluno, Long idTaskClass) {
    return cacheRespostasTaskClass.computeIfAbsent(
        idAluno + "-" + idTaskClass,
        chave -> respostasDeterminadoAluno.buscaRespostas(idAluno, idTaskClass));
  }

  /*
   * #paraBlogar o unsupportedOperation que tinha aqui guiou no código. A
   * exception foi lançada e eu tive a oportunidade de corrigir.
   */
  @Override
  public Collection<RespostaLearningTask> buscaRespostasLearningTask(
      Long idAluno, Long idLearningTask) {
    return cacheRespostasLearningTask.computeIfAbsent(
        idAluno + "-" + idLearningTask,
        chave -> respostasDeterminadoAluno.buscaRespostasLearningTask(idAluno, idLearningTask));
  }

  @Override
  public Optional<RespostaLearningTask> buscaUltimaRespostaDada(Long idAluno, Long idLearningTask) {

    return cacheUltimasRespostasLearningTask.computeIfAbsent(
        idAluno + "-" + idLearningTask,
        chave -> respostasDeterminadoAluno.buscaUltimaRespostaDada(idAluno, idLearningTask));
  }
}
