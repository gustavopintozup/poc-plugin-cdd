package br.com.zup.lms.alunos;

import static javax.persistence.GenerationType.IDENTITY;

import br.com.zup.lms.admin.partials.learningtask.LearningTask;
import br.com.zup.lms.admin.partials.treinamento.Treinamento;
import br.com.zup.lms.compartilhado.infra.Generated;
import br.com.zup.lms.compartilhado.infra.ICP;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.util.Assert;

@Table(name = "alunos")
@Entity
@ICP(8)
public class Aluno {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotBlank
  @Email
  @Column(unique = true)
  private String email;

  @NotBlank
  @Column(nullable = false)
  private String nome;

  @OneToMany(mappedBy = "aluno")
  /*
   * Alberto: Queria ter usado o LazyCollection aqui, mas aí o contains deixa
   * de realizar a lógica em memória e vai realizar a lógica contra o banco de
   * dados. Dessa forma quebraria o método podeAcessarTreinamento
   */
  @ICP
  private Set<Inscricao> inscricoes = new HashSet<>();

  @OneToMany(mappedBy = "aluno")
  /*
   * Alberto: Talvez o melhor fosse usar um SortedSet ordenado pela data de
   * envio da resposta. Atualmente usamos a ordenacao padrão do banco de
   * dados, que é a pk.
   */
  @ICP
  private List<RespostaLearningTask> respostas = new ArrayList<>();

  // 1
  @OneToMany(mappedBy = "aluno")
  @OrderBy("instanteCriacao DESC")
  private final SortedSet<Certificado> certificados = new TreeSet<>();

  public Aluno(@NotBlank @Email String email, @NotBlank String nome) {
    Assert.hasText(email, "O email do aluno não pode estar vazio");
    Assert.hasText(nome, "O nome do aluno não pode estar vazio");
    this.email = email;
    this.nome = nome;
  }

  /** @deprecated só para o JPA */
  @Deprecated(since = "1.0")
  public Aluno() {}

  public Long getId() {
    return id;
  }

  public @NotBlank @Email String getEmail() {
    return email;
  }

  public String getNome() {
    return nome;
  }

  /*
   * Alberto: Eu quase implementei o método isInscrito e a implementação seria
   * igual a essa... Como saber se um código que você vai fazer já existe na
   * base de código? Modelo de aprendizagem de máquina?
   */
  public boolean podeAcessarTreinamento(@ICP TemTreinamento temTreino) {
    return this.inscricoes.contains(new Inscricao(this, temTreino.getTreinamento()));
  }

  @Override
  @Generated(Generated.IDE)
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return email.equals(aluno.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  public boolean estaInscritoEmAlgumTreinamento() {
    return !this.inscricoes.isEmpty();
  }

  public Optional<LearningTask> ultimaLearningTaskRespondida() {
    // @ICP
    if (this.respostas.isEmpty()) {
      return Optional.empty();
    }

    // era realmente melhor que fosse um SortedSet
    return Optional.of(this.respostas.get(this.respostas.size() - 1).getLearningTask());
  }

  public boolean finalizou(
      @ICP LearningTask learningTask, @ICP RespostasDeterminadoAluno respostas) {

    return respostas.buscaRespostasLearningTask(this.id, learningTask.getId()).iterator().hasNext();
  }

  // 1
  public Optional<Certificado> buscaUltimoCertificadoPeloTreinamento(Treinamento treinamento) {
    Assert.notNull(treinamento, "Treinamento é obrigatório");
    return this.certificados.stream()
        .filter(certificado -> certificado.pertenceAoTreinamento(treinamento))
        .max(Comparator.comparing(Certificado::getInstanteCriacao));
  }

  public Optional<RespostaLearningTask> buscaUltimaResposta(
      LearningTask learningTask, RespostasDeterminadoAluno respostasDeterminadoAluno) {

    return respostasDeterminadoAluno.buscaUltimaRespostaDada(this.id, learningTask.getId());
  }
}
