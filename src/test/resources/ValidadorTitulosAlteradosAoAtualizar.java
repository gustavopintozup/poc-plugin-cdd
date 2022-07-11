package br.com.zup.lms.admin;

import br.com.zup.lms.admin.partials.treinamento.Treinamento;
import br.com.zup.lms.compartilhado.infra.ICP;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * Aplica a validação para identificar se o título de alguma TaskClass ou de alguma LearningTask foi
 * alterado na atualização do Treino. Esse validador não é um validador padrão do Spring, pois era
 * necessário os dois treinamentos pra ocorrer a validação, sendo o treinamento atualizado só
 * disponível dentro do método do Controller. Essa classe lança BindException nas validações
 *
 * @author jackson.mota
 */
@ICP(7)
public class ValidadorTitulosAlteradosAoAtualizar {

  // 2
  public void validate(
      BindingResult errors, Treinamento treinamentoAntigo, Treinamento treinamentoAtualizado)
      throws BindException {
    // 1
    if (errors.hasErrors()) {
      throw new BindException(errors);
    }
    Assert.state(
        treinamentoAntigo.equals(treinamentoAtualizado),
        "Para validar o título de alguma TaskClass ou de alguma LearningTask, é necessário que o Treino atual seja igual ao Treino antigo.");

    // 1
    var taskClassesAlteradas =
        treinamentoAntigo.identificaTaskClassesComTitulosAlterados(treinamentoAtualizado);

    // 1
    if (!taskClassesAlteradas.isEmpty()) {
      taskClassesAlteradas.forEach(
          taskClassAlterada ->
              errors.reject(
                  null,
                  "Os tiulos de task class não podem ser alterados. O titulo da TaskClass: '"
                      + taskClassAlterada.getTitulo()
                      + "' foi alterado"));
      throw new BindException(errors);
    }

    // 1
    var learningTasksAlteradas =
        treinamentoAntigo.identificaLearningTasksComTitulosAlterados(treinamentoAtualizado);

    // 1
    if (!learningTasksAlteradas.isEmpty()) {
      learningTasksAlteradas.forEach(
          learningTaskAlterada ->
              errors.reject(
                  null,
                  "Os tiulos de learning tasks não podem ser alterados. O titulo da LearningTask: '"
                      + learningTaskAlterada.getTitulo()
                      + "' foi alterado"));
      throw new BindException(errors);
    }
  }
}
