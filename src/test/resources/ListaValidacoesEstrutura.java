package br.com.zup.lms.admin.partials.validaEstruturaTaskClassVisitor;

import br.com.zup.lms.admin.HeadingWrapper;
import br.com.zup.lms.admin.ValidaParteEstruturaMarkdownTaskClass;
import br.com.zup.lms.compartilhado.infra.ICP;
import br.com.zup.lms.compartilhado.infra.PartialClass;
import com.vladsch.flexmark.ast.Heading;
import java.util.List;

@ICP(9)
@PartialClass(ValidaEstruturaTaskClassVisitor.class)
public class ListaValidacoesEstrutura implements ValidaParteEstruturaMarkdownTaskClass {

  @ICP private final List<ValidaParteEstruturaMarkdownTaskClass> validadores;

  @ICP(5)
  public ListaValidacoesEstrutura(ValidaEstruturaTaskClassVisitor origem) {
    validadores =
        List.of(
            new ValidaEstruturaBasica(origem),
            new ValidaDescricaoTaskClass(origem),
            new ValidaHeadersFilhosDeAjudas(origem),
            new ValidaConteudoDasAjudas(origem),
            new ValidaTituloEConteudoLearningTasks(origem),
            new ValidaConfiguracoesLearningTask(origem));
  }

  @Override
  public boolean continua(Heading heading, @ICP HeadingWrapper headingWrapper) {
    // @ICP(1)
    for (ValidaParteEstruturaMarkdownTaskClass validador : validadores) {
      // ICP(1)
      if (!validador.continua(heading, headingWrapper)) {
        return false;
      }
    }

    return true;
  }
}
