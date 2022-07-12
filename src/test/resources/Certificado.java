package br.com.zup.lms.alunos;

import static javax.persistence.GenerationType.IDENTITY;

import br.com.zup.lms.admin.partials.treinamento.Treinamento;
import br.com.zup.lms.compartilhado.infra.ICP;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

@Table(name = "certificados")
@Entity
@ICP()
public class Certificado implements Comparable<Certificado> {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(nullable = false)
  private Long id;

  @NotNull @ManyToOne private Aluno aluno;

  @NotBlank
  @Column(nullable = false, updatable = false)
  private String nomeAluno;

  @NotBlank
  @Column(nullable = false, updatable = false)
  @Email
  private String emailAluno;

  @NotNull @ManyToOne private Treinamento treinamento;

  @NotBlank
  @Column(nullable = false, updatable = false)
  private String tituloTreinamento;

  @NotBlank
  @Column(nullable = false, updatable = false)
  private String descritivoTreinamento;

  @NotNull
  @Column(nullable = false, updatable = false)
  private final Instant instanteCriacao = Instant.now();

  public Certificado(
      @NotNull @Valid Treinamento treinamento,
      @NotNull @Valid Aluno aluno,
      @NotNull RespostasDeterminadoAluno respostas) {
    Assert.notNull(treinamento, "Treinamento é obrigatório aqui");
    Assert.notNull(aluno, "Aluno é obrigatório aqui");
    Assert.notNull(respostas, "Respostas é obrigatório aqui");

    Assert.isTrue(
        treinamento.treinamentoConcluidoParaAluno(aluno, respostas),
        "Treinamento precisa estar concluido para o certificado ser gerado");

    this.nomeAluno = aluno.getNome();
    this.emailAluno = aluno.getEmail();
    this.aluno = aluno;
    this.tituloTreinamento = treinamento.getTitulo();
    this.descritivoTreinamento = treinamento.getDescritivoParaCertificado();
    this.treinamento = treinamento;
  }

  /** @deprecated só para o JPA */
  @Deprecated(since = "1.0")
  public Certificado() {}

  public Long getId() {
    return id;
  }

  public String getNomeAluno() {
    return nomeAluno;
  }

  public String getTituloTreinamento() {
    return tituloTreinamento;
  }

  public String getDescritivoTreinamento() {
    return descritivoTreinamento;
  }

  public String getEmailAluno() {
    return emailAluno;
  }

  public Instant getInstanteCriacao() {
    return instanteCriacao;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public Treinamento getTreinamento() {
    return treinamento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Certificado that = (Certificado) o;
    return Objects.equals(aluno, that.aluno)
        && Objects.equals(treinamento, that.treinamento)
        && Objects.equals(instanteCriacao, that.instanteCriacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aluno, treinamento, instanteCriacao);
  }

  public boolean pertenceAoTreinamento(Treinamento treinamento) {
    Assert.notNull(treinamento, "Treinamento é obrigatório");
    return this.treinamento.equals(treinamento);
  }

  @Override
  public int compareTo(Certificado outroCertificado) {
    return this.instanteCriacao.compareTo(outroCertificado.instanteCriacao);
  }

  public boolean pertenceAoAluno(Aluno aluno) {
    Assert.notNull(aluno, "Aluno é obrigatório");
    return this.aluno.equals(aluno);
  }
}
