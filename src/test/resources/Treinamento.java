package br.com.zup.lms.admin.partials.treinamento;

import br.com.zup.lms.admin.TaskClassParserDTO;
import br.com.zup.lms.admin.partials.learningtask.LearningTask;
import br.com.zup.lms.admin.partials.taskclass.TaskClass;
import br.com.zup.lms.alunos.Aluno;
import br.com.zup.lms.alunos.RespostasDeterminadoAluno;
import br.com.zup.lms.alunos.StatusTreinamento;
import br.com.zup.lms.compartilhado.Percentual;
import br.com.zup.lms.compartilhado.infra.Generated;
import br.com.zup.lms.compartilhado.infra.ICP;
import org.hibernate.annotations.Type;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.zup.lms.alunos.StatusTreinamento.CONCLUIDO;
import static br.com.zup.lms.compartilhado.infra.Generated.IDE;
import static java.util.List.copyOf;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Aqui representa a ideia de um treinamento dentro do sistema.
 *
 * @author albertoluizsouza
 */
@Table(name = "treinamentos")
@Entity
@ICP(10)
public class Treinamento {

  // 5
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @ICP(0.5)
  private Long id;

  @NotBlank
  @Column(nullable = false, unique = true)
  @ICP(0.5)
  private String tituloOriginal;

  @NotBlank
  @Column(nullable = false, unique = true)
  @ICP(0.5)
  private String titulo;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(nullable = false)
  @ICP(0.5)
  private String descricao;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(name = "capacidades_a_serem_habilitadas_original")
  // #bug talvez tenha um bug na lib de vlad na hora de snake casear uma
  // string com duas consoantes juntinhas
  @ICP(0.5)
  private String capacidadesASeremHabilitadasOriginal;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(nullable = false)
  @ICP(0.5)
  private String preRequisitosOriginal;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(nullable = false)
  @ICP(0.5)
  private String ementaOriginal;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(nullable = false)
  @ICP(0.5)
  private String descritivoParaCertificado;

  @NotNull
  @Size(min = 1)
  /*
   * #naoSeiExplicar Alberto: se eu não coloco o cascade merge ele
   * estava soltava um NPE no compareTo da TaskClass. Basicamente aparecia
   * uma task class com todos atributos nulos.
   */
  @OneToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      mappedBy = "relacionamentos")
  @OrderBy("identificadorOrdenacao")
  @ICP
  SortedSet<TaskClass> taskClasses = new TreeSet<>();

  /**
   * @deprecated Apenas para a JPA
   */
  @Deprecated(since = "1.0")
  public Treinamento() {}

  @ICP
  public Treinamento(
      String tituloOriginal,
      String titulo,
      String descricao,
      String ementaOriginal,
      String mdComPreRequisito,
      String capacidadesASeremHabilitadasOriginal,
      String descritivoParaCertificado,
      @ICP Collection<TaskClassParserDTO> dadosDeTodasTaskClasses) {

    Assert.hasText(
        tituloOriginal, "A string do titulo em md do treinamento não pode estar nula ou vazia");
    Assert.hasText(titulo, "A string do titulo do treinamento não pode estar nula ou vazia");
    Assert.hasText(descricao, "A string da descricão do treinamento não pode estar nula ou vazia");
    Assert.hasText(
        ementaOriginal, "A string da ementa em md do treinamento não pode estar nula ou vazia");
    Assert.hasText(
        mdComPreRequisito,
        "A string dos pre-requisitos em md do treinamento não pode estar nula ou vazia");
    Assert.hasText(
        capacidadesASeremHabilitadasOriginal,
        "A string das capacidades em md do treinamento não pode estar nula ou vazia");
    Assert.notNull(
        dadosDeTodasTaskClasses, "A lista de task classes do treinamento não pode ser nula");
    Assert.state(
        dadosDeTodasTaskClasses.iterator().hasNext(),
        "A lista de task classes do treinamento não pode estar vazia");
    Assert.hasText(
        descritivoParaCertificado,
        "A string do descritivo para certificado em md do treinamento não pode estar nula ou"
            + " vazia");

    this.tituloOriginal = tituloOriginal;
    this.titulo = titulo;
    this.descricao = descricao;
    this.ementaOriginal = ementaOriginal;
    this.preRequisitosOriginal = mdComPreRequisito;
    this.capacidadesASeremHabilitadasOriginal = capacidadesASeremHabilitadasOriginal;
    this.descritivoParaCertificado = descritivoParaCertificado;

    dadosDeTodasTaskClasses.forEach(
        dadosTaskClassEspecifica ->
            Assert.state(
                this.taskClasses.add(dadosTaskClassEspecifica.toTaskClass(this)),
                "Aparentemente existe uma task class com o mesmo identificador de ordenacao ou"
                    + " igual a outra"));
  }

  @Generated(IDE)
  public Long getId() {
    return id;
  }

  public String getTituloOriginal() {
    return tituloOriginal;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getCapacidadesASeremHabilitadasOriginal() {
    return capacidadesASeremHabilitadasOriginal;
  }

  public String getPreRequisitosOriginal() {
    return preRequisitosOriginal;
  }

  public String getEmentaOriginal() {
    return ementaOriginal;
  }

  public String getDescritivoParaCertificado() {
    return descritivoParaCertificado;
  }

  public List<TaskClass> getTaskClasses() {
    return copyOf(taskClasses);
  }

  @Override
  @Generated(Generated.IDE)
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((tituloOriginal == null) ? 0 : tituloOriginal.hashCode());
    return result;
  }

  @Generated(Generated.IDE)
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Treinamento other = (Treinamento) obj;
    if (tituloOriginal == null) {
      if (other.tituloOriginal != null) return false;
    } else if (!tituloOriginal.equals(other.tituloOriginal)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Treinamento [id=" + id + ", tituloOriginal=" + tituloOriginal + "]";
  }

  @ICP(3)
  public boolean verificaSeATaskClassAnteriorFoiFinalizada(
      TaskClass taskClassCorrente,
      @ICP Aluno aluno,
      @ICP RespostasDeterminadoAluno respostasDeterminadoAluno) {
    // 1
    return new Treinamento$Partial1(this)
        .verificaSeATaskClassAnteriorFoiFinalizada(
            taskClassCorrente, aluno, respostasDeterminadoAluno);
  }

  public Optional<TaskClass> proximaTaskClassNaoFinalizada(
      Aluno aluno, RespostasDeterminadoAluno respostasDeterminadoAluno) {
    return taskClasses.stream()
        .filter(taskClass -> !taskClass.foiFinalizadaPeloAluno(aluno, respostasDeterminadoAluno))
        .findFirst();
  }

  public Optional<TaskClass> ultimaTaskClassComLearningTaskRespondida(
      Aluno aluno, RespostasDeterminadoAluno respostasDeterminadoAluno) {

    LinkedList<TaskClass> resultado =
        taskClasses.stream()
            .filter(
                taskClass -> taskClass.temLearningTaskRespondida(aluno, respostasDeterminadoAluno))
            .collect(Collectors.toCollection(LinkedList::new));

    return Optional.ofNullable(resultado.peekLast());
  }

  /**
   * @param aluno
   * @param respostasAluno
   * @return ideia de percentual concluido de um treinamento por uma pessoa aluna
   */
  public Percentual percentualConcluido(Aluno aluno, RespostasDeterminadoAluno respostasAluno) {
    long quantidade = this.taskClasses.size();
    long quantidadeFinalizada =
        this.taskClasses.stream()
            .filter(taskClass -> taskClass.foiFinalizadaPeloAluno(aluno, respostasAluno))
            .count();
    return new Percentual(new BigDecimal(quantidadeFinalizada), new BigDecimal(quantidade));
  }

  public int posicaoTaskClass(TaskClass taskClassProcurada) {
    Assert.isTrue(
        taskClassProcurada.pertenceAoTreinamento(this),
        "Para calcular a posição da task class, passe uma task class que pertence ao treinamento");
    return this.taskClasses.headSet(taskClassProcurada).size() + 1;
  }

  /**
   * @param taskClassAtual
   * @return retorna buscal pela proxima {@link TaskClass} depois da passada como argumento
   */
  public Optional<TaskClass> buscaProximaTaskClass(TaskClass taskClassAtual) {
    Assert.isTrue(
        taskClassAtual.pertenceAoTreinamento(this),
        "Para buscar a próxima task class, passe uma task class que pertence ao treinamento");
    return Optional.ofNullable(new TreeSet<>(this.taskClasses).higher(taskClassAtual));
  }

  public StatusTreinamento getStatusTreinamento(
      Aluno aluno, RespostasDeterminadoAluno respostasDeDeterminadoAluno) {
    return new Treinamento$Partial1(this).getStatusTreinamento(aluno, respostasDeDeterminadoAluno);
  }

  public boolean treinamentoConcluidoParaAluno(Aluno aluno, RespostasDeterminadoAluno respostas) {
    return CONCLUIDO.equals(this.getStatusTreinamento(aluno, respostas));
  }

  public Treinamento atualiza(Treinamento novaVersao) {
    this.titulo = novaVersao.titulo;
    this.tituloOriginal = novaVersao.tituloOriginal;
    this.descricao = novaVersao.descricao;
    this.capacidadesASeremHabilitadasOriginal = novaVersao.capacidadesASeremHabilitadasOriginal;
    this.preRequisitosOriginal = novaVersao.preRequisitosOriginal;
    this.ementaOriginal = novaVersao.ementaOriginal;
    this.descritivoParaCertificado = novaVersao.descritivoParaCertificado;

    new AtualizaTaskClasses(this).atualiza(novaVersao);

    return this;
  }

  public Optional<TaskClass> buscaTaskClassPeloTitulo(String tituloTaskClass) {
    return this.taskClasses.stream()
        .filter(taskClass -> taskClass.existePorTitulo(tituloTaskClass))
        .findFirst();
  }

  public Collection<TaskClass> identificaTaskClassesComTitulosAlterados(
      Treinamento novaVersaoDoTreinamento) {
    Assert.state(
        this.equals(novaVersaoDoTreinamento),
        "Para identificar alterações nos títulos de task classes, o treinamento deve ser o mesmo");

    return this.taskClasses.stream()
        .filter(
            essaTaskClass ->
                novaVersaoDoTreinamento.taskClasses.stream()
                    .noneMatch(essaTaskClass::temMesmoTitulo))
        .toList();
  }
  // 1
  public Collection<LearningTask> identificaLearningTasksComTitulosAlterados(
      Treinamento novaVersaoDoTreinamento) {
    Assert.state(
        this.equals(novaVersaoDoTreinamento),
        "Para identificar alterações nos títulos de learning tasks, o treinamento deve ser o mesmo");

    return this.taskClasses.stream()
        .filter(
            taskClassDoTreinamentoExistente ->
                novaVersaoDoTreinamento.getTaskClasses().stream()
                    .anyMatch(
                        outraTaskClass ->
                            outraTaskClass.temMesmoTitulo(taskClassDoTreinamentoExistente)))
        .flatMap(
            taskClassDoTreinamentoExistente -> {
              var taskClassNovaVersaoTreinamento =
                  novaVersaoDoTreinamento.buscaTaskClassPeloTitulo(
                      taskClassDoTreinamentoExistente.getTitulo());
              Assert.state(
                  taskClassNovaVersaoTreinamento.isPresent(),
                  "TaskClass deveria existir aqui, por conta do filter acima");

              return taskClassDoTreinamentoExistente
                  .identificaLearningTaskComTitulosAlterados(taskClassNovaVersaoTreinamento.get())
                  .stream();
            })
        .toList();
  }
}
