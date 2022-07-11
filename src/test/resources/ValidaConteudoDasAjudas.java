package br.com.zup.lms.admin.partials.validaEstruturaTaskClassVisitor;

import br.com.zup.lms.admin.HeadingWrapper;
import br.com.zup.lms.admin.TiposDeAjuda;
import br.com.zup.lms.admin.ValidaParteEstruturaMarkdownTaskClass;
import br.com.zup.lms.compartilhado.infra.PartialClass;
import com.vladsch.flexmark.ast.Heading;
import org.springframework.util.StringUtils;

/**
 * Partial class da class {@link ValidaEstruturaTaskClassVisitor}
 *
 * @author albertoluizsouza
 */
// 10
@PartialClass(ValidaEstruturaTaskClassVisitor.class)
public class ValidaConteudoDasAjudas implements ValidaParteEstruturaMarkdownTaskClass {

  // 1
  private final ValidaEstruturaTaskClassVisitor origem;

  public ValidaConteudoDasAjudas(ValidaEstruturaTaskClassVisitor origem) {
    this.origem = origem;
  }

  @Override
  public boolean continua(Heading heading, HeadingWrapper headingWrapper) {
    // 1
    if (origem.headingCache.getOrDefault(2, "header-que-nao-existe").equals("ajudas")
        && heading.getLevel() == 4) {

      // 2
      if (!origem.headingCache.get(3).equals("teorias-necessarias")
          && !origem.headingCache.get(3).equals("part-task-practice")) {
        origem.adicionaErro(
            "Dentro das ajudas, se o header é de nível de 4 o nível 3 precisa ser"
                + " teorias-necessarias ou part-task-practice. Encontramos esse aqui: "
                + origem.headingCache.get(3));
      }
      // 1
      else {
        // 1
        if (!StringUtils.hasText(headingWrapper.descricao())) {
          origem.adicionaErro("A ajuda de título " + headingWrapper.titulo + " está sem descrição");
        }

        // 1
        else {
          switch (origem.headingCache.get(3)) {
              // 1
            case "teorias-necessarias" -> origem.tiposLearningTaskEAjudas.add(
                TiposDeAjuda.TEORIA_NECESSARIA);
              // 1
            case "part-task-practice" -> origem.tiposLearningTaskEAjudas.add(
                TiposDeAjuda.PART_TASK_PRACTICE);
              // 1
            default -> throw new IllegalArgumentException(
                "Não era para ter chegado no final do switch: " + origem.headingCache.get(3));
          }
        }
      }
    }
    return true;
  }
}
