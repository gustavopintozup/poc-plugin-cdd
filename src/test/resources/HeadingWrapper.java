package br.com.zup.lms.admin;

import br.com.zup.lms.compartilhado.infra.ICP;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@ICP(9)
public class HeadingWrapper {

  @NotNull private final Heading heading;
  public final String titulo;

  public HeadingWrapper(@NotNull Heading heading) {
    this.heading = heading;

    // aliviando na pre condicao para que o header possa ser trabalhado
    var headingContent = this.heading.getFirstChild();
    // 1
    if (headingContent == null) {
      this.titulo = "";
      // 1
    } else {
      this.titulo = Optional.ofNullable(headingContent.getChars().toStringOrNull()).orElse("");
    }
  }

  // 2
  public String descricao() {
    var todoTexto = new StringBuilder();

    executaAcaoSobreAsLinhas(todoTexto::append);

    return todoTexto.toString();
  }
  
  public String descricao(Function<String, String> trataLinha) {
	  var todoTexto = new StringBuilder();
	  
	  executaAcaoSobreAsLinhas(linha -> {
		  todoTexto.append(trataLinha.apply(linha.toString()));
	  });
	  
	  return todoTexto.toString();
  }

  private void executaAcaoSobreAsLinhas(Consumer<BasedSequence> funcaoAcao) {
    var proximoNode = this.heading.getNext();

    // 2
    while (proximoNode != null && !(proximoNode instanceof Heading)) {
      funcaoAcao.accept(proximoNode.getChars());
      proximoNode = proximoNode.getNext();
    }
  }

  public List<String> linhas() {
    ArrayList<String> linhas = new ArrayList<>();
    executaAcaoSobreAsLinhas(
        linha -> {
          @Nullable String conteudo = linha.toStringOrNull();
          Assert.state(
              Objects.nonNull(conteudo),
              "Não deveria ter linha que por algum motivo retorna nulo no header " + titulo);
          linhas.add(conteudo);
        });
    return linhas;
  }

  // 2
  public LearningTaskDTO novaLearningTask() {

    Assert.state(
        StringUtils.hasText(titulo),
        "Não deveria criar uma learning task se o título está vazio ou nulo");
    Assert.state(
        titulo.contains(":"),
        "O titulo de uma learning task precisa ser composto por tipo:titulo -> " + titulo);

    var tipoETitulo = titulo.split(":");
    // 1
    var tipo = TipoLearningTask.valueOf(tipoETitulo[0].toUpperCase());

    var descricaoLearningTask = this.descricao(linha -> {
    	String quebraLinhaTratada = new TransformaEmParagrafroDeMarkdown().apply(linha);
    	return new SubstituiMarcacaoDeCodigoPorMarcacaoComQuebraDeLinha().apply(quebraLinhaTratada);
    });

    // 1
    return new LearningTaskDTO(
        tipo, heading.getChars().toString(), tipoETitulo[1], descricaoLearningTask);
  }
}
