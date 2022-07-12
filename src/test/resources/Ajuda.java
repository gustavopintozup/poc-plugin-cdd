package br.com.zup.lms.admin;

import br.com.zup.lms.admin.partials.taskclass.TaskClass;
import br.com.zup.lms.compartilhado.infra.Generated;
import br.com.zup.lms.compartilhado.infra.ICP;
import org.hibernate.annotations.Type;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static br.com.zup.lms.compartilhado.infra.Generated.IDE;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Representa uma ajuda de uma task class. Toda task class pode ter uma ou mais ajudas de diferentes
 * tipos.
 *
 * <p>Nota recomendada pela documentação da interface {@link Comparable}: Esta classe tem uma
 * ordenação natural diferente do critério utilizado pelo equals.
 *
 * @author albertoluizsouza
 */
@Table(
    name = "ajudas",
    uniqueConstraints =
        @UniqueConstraint(
            name = "uc_ajudas_identificador_ordenacao_task_class_id",
            columnNames = {"task_class_id", "identificador_ordenacao"}))
@Entity
@ICP(2)
public class Ajuda implements Comparable<Ajuda> {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  // 1
  private TiposDeAjuda tipoAjuda;

  @NotBlank
  @Column(nullable = false)
  private String tituloOriginal;

  @NotBlank
  @Column(nullable = false)
  private String titulo;

  @NotBlank
  @Lob
  @Type(type = "org.hibernate.type.TextType")
  @Column(nullable = false)
  private String descricaoOriginal;

  @ManyToOne(optional = false)
  @JoinColumn(name = "task_class_id")
  // 1
  private TaskClass taskClass;

  @NotBlank
  @Column(nullable = false, name = "identificador_ordenacao")
  private String identificadorOrdenacao;

  @Deprecated
  public Ajuda() {}

  public Ajuda(
      @Valid @NotNull TaskClass taskClass,
      String identificadorOrdenacao,
      TiposDeAjuda tipoAjuda,
      String tituloOriginal,
      String titulo,
      String descricaoOriginal) {
    Assert.notNull(taskClass, "A task class não pode ser nula");
    Assert.hasText(identificadorOrdenacao, "O identificador de ordenacao precisa de um texto");
    Assert.notNull(tipoAjuda, "O tipo de ajuda da Ajuda não pode ser nulo");
    Assert.hasText(tituloOriginal, "O titulo da ajuda precisa conter texto");

    this.taskClass = taskClass;
    this.identificadorOrdenacao = identificadorOrdenacao;
    this.tipoAjuda = tipoAjuda;
    this.tituloOriginal = tituloOriginal;
    this.titulo = titulo;
    this.descricaoOriginal = descricaoOriginal;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricaoOriginal() {
    return descricaoOriginal;
  }

  public boolean filtraPorTipo(TiposDeAjuda tipoAjudaBuscado) {
    return this.tipoAjuda.equals(tipoAjudaBuscado);
  }

  @Override
  public int compareTo(Ajuda outraAjuda) {
    Assert.isTrue(
        this.taskClass.equals(outraAjuda.taskClass),
        "Não faz sentido comparar ajudas de task classes diferentes");
    return this.identificadorOrdenacao.compareTo(outraAjuda.identificadorOrdenacao);
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = prime * result + ((taskClass == null) ? 0 : taskClass.hashCode());
    result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
    return result;
  }

  @Generated(IDE)
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Ajuda other = (Ajuda) obj;
    if (taskClass == null) {
      if (other.taskClass != null) return false;
    } else if (!taskClass.equals(other.taskClass)) return false;
    if (titulo == null) {
      return other.titulo == null;
    } else return titulo.equals(other.titulo);
  }

  public void atualiza(Ajuda novaAjuda) {
    Assert.isTrue(
        novaAjuda.taskClass.equals(this.taskClass),
        "Não rola atualizar uma ajuda com uma ajuda de outra task class");

    this.descricaoOriginal = novaAjuda.descricaoOriginal;
    this.identificadorOrdenacao = novaAjuda.identificadorOrdenacao;
    this.tipoAjuda = novaAjuda.tipoAjuda;
    this.titulo = novaAjuda.titulo;
    this.tituloOriginal = novaAjuda.tituloOriginal;
  }

  public void relacionaComTaskClassPersistida(TaskClass taskClass) {
    Assert.isTrue(Objects.nonNull(taskClass.getId()), "Não rola trocar para uma task class sem id");
    Assert.isTrue(
        this.taskClass.equals(taskClass),
        "Tem que relacionar com uma task class persistida, porém igual a que já existia");

    this.taskClass = taskClass;
  }
}
