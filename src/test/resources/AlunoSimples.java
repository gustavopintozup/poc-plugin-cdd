package br.com.zup.lms.alunos;

import java.util.Objects;


public class Aluno {
  private Long id;
  private String email;
  private String nome;

  public Aluno(String email, String nome) {
    this.email = email;
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Aluno aluno = (Aluno) o;
    return email.equals(aluno.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
