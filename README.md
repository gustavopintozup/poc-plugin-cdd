# Cognitive-Driven Development (CDD)

Cognitive-Driven Development (CDD) é uma técnica de design de código que visa reduzir o esforço cognitivo que devs empregam na compreensão de uma determinada unidade de código, por exemplo, uma classe. 

A ideia que CDD defende é que deve existir um limite no tamanho das unidades de código, mas esse limite deve ser definido de forma disciplinada.

Para saber mais sobre o CDD, leia [aqui](https://www.zup.com.br/blog/cognitive-driven-development-cdd).

## Plugin do CDD

Essa é uma POC de um plugin que calcula os ICPs do CDD usando a ferramenta de análise estática [spoon](https://github.com/INRIA/spoon). 

Por limitação da ferramenta de análise estática, o plugin analise somente código válido Java.

## ICPs

ICPs são elementos de código que podem afetar o entendimento das pessoas desenvolvedoras, de acordo com sua frequência de uso. Exemplos de ICPs são:

- Condicionais de código (como *if–else* ou *switch-case*);
- Laços de repetição (como *for* ou *while*);
- Tratadores de exceção (como *try-catch*).

Isoladamente, estes elementos de código podem não embaraçar o entendimento de um trecho. No entanto, se usados com frequência em uma mesma unidade de código, eles potencialmente podem dificultar o seu entendimento.]

Atualmente o plugin computa os ICPs abaixo. O valor entre parênteses é a chave que deve ser utilizada no arquivo de configuração `cdd.json` para habilitar o ICP.


### Métodos (METHOD)

- [x] Contabiliza o número de métodos de uma classe.

```java
  public Long getId() {
    return id;
  }

  public getEmail() {
    return email;
  }

  public String getNome() {
    return nome;
  }
```

No exemplo acima, contabilizamos 3 (três) métodos. Não contabilizamos construtores ou métodos gerados automáticamente (como o `equals` e o `hashcode`). Para contabilizar o `equals` e o `hashcode`, use a flag `"methodsAutoGenerated": true` no arquivo de configuração `cdd.json`.

### Anotação (ANNOTATION)

- [X] Contabiliza o número de anotações de uma classe.

```java
@Table(name = "alunos")
@Entity
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
  //...
}
```

No exemplo acima, embora existam 9 (nove) usos de anotação, o plugin contará apenas 7 (sete), isto pois a contabilização é feita somente da primeira vez que se usa uma anotação. Uso subsequentes da mesma anotação **não** aumentam a contabilização dos ICPs.

Todos os usos de anotação são contabilizados, incluindo: anotações de classe, método, váriáveis de classe e parâmetros de método.

### Desvios (IF_STATEMENT)

- [X]  Contabiliza o número de `if` e `else` que em uma classe. Também contabilizam o uso de operadores ternários.

```java
if (a > b) {
  // ...
} else if (b > c) {
  // ...
} else if (c > d) {
  // ...
} else {
  // ...
}

String result = (e > 40) ? "pass" : "fail";
boolean x = (1 > 0) ? false : true;
```

O código acima contabilizaria 8 (oito) usos de ICPs: 4 (quatro) nos primeiros ifs e outros 4 (quatro) nos dois operadores ternários ao final.

O plugin também contabiliza uso de `switch`s e seus `cases`.

### Tratadores de exceção (TRY_CATCH_STATEMENT)

- [X] Contabiliza o número de instruções `try`, `catch` e `finally` em uma classe.

No código abaixo, o plugin contabilizaria 3 (três) ICPs: um para o *try*, outro para o *catch* e um para o *finally*. 

```java
try {
  //...
}
catch (Exception | CDDException e) {
  //...
}
finally {
  //...
}
```

A decisão para contabilizar somente 1 ICP, mesmo usando multicatch, é devido ao fato de que não se abre um novo ramo no código para tratar as duas exceções. 

Por outro lado, se estivessemos com dois *catch*s, como no exemplo a baixo, contaríamos 2 (dois) ICPS (um pra cada *catch*), além dos 2 (dois) outros ICPs (um para o *try* e outro para o *finally*).

```java
try {
  //...
}
catch (CDDException e) {
  //...
} catch (Exception e) {
  //...
}
finally {
  //...
}
```

- [X] THROW_STATEMENT: Contabiliza a quantidade de `throw` nos  `try`s de uma classe;
- [X] WHILE_STATEMENT: Contabiliza a quantidade de laços do tipo `while` em uma classe;
- [X] FOR_STATEMENT: Contabiliza a quantidade de laços do tipo `for` em uma classe;
- [X] FOREACH_STATEMENT: Contabiliza a quantidade de laços do tipo `foreach` em uma classe;
- [X] LAMBDA_EXPRESSION: Contabiliza a quantidade de expressões `lambda` em uma classe;
- [X] YIELD_STATEMENT: Contabiliza a quantidade de `yield` em `switch` de uma classe;
- [X] SUPER_EXPRESSION: Contabiliza a quantidade de chamadas ao `super` em uma classe; 
- [X] ANONYMOUS_CLASS: Contabiliza a quantidade de uso de classes anonimas em uma classe;
- [X] LOCAL_VARIABLE: Contabiliza a quantidade de variáveis definidas no escopo de uma classe;


## Arquivo de configuração (cdd.json)

Para listar os ICPs que se deseja contabilizar, estes precisam estar definidos no arquivo de configuração cdd.json, que deve estar definido na raiz do seu projeto. Um exemplo de arquivo de configuração pode ser visto a seguir:

```json
{
  "limitOfComplexity": 10,
  "rules": [
    {
      "name": "IF_STATEMENT",
      "cost": 1
    },
    {
      "name": "TRY_CATCH_STATEMENT",
      "cost": 1
    }
  ]
}
```

Nesse arquivo, considera-se somente dois ICPs (ifs e tratadores de exceção), que tem o mesmo peso (1). Um arquivo cdd.json mais completo pode ser [acessado aqui](https://github.com/gustavopintozup/poc-plugin-cdd/blob/main/cdd.json).

## ICPs que devem ser implementados no futuro

- [ ] TYPE_CAST_EXPRESSION("type_cast_expression")
- [ ] IMPLICIT_VARIABLE("implicit_variable")
- [ ] IMPORT_STATIC_REFERENCE_ELEMENT("import_static_reference_element")
- [ ] C_LASS_INITIALIZER("c_lass_initializer"), 
- [ ] CLASS("class")
- [ ] METHOD_CALL_EXPRESSION("method_call_expression")


## Duvidas
- Como identificar um static import
- Como identificar um cast

## Todo
- Calcular os pesos dos ICPs no prettyprint()
- Implementar o acoplamento contextual (externo as classes)

## Problemas conhecidos 

- A contabilização de ifs não considera o `else if` (apenas `if` e `else`)
- Os testes de unidade da classe `MetricasCDDTest` estão com compartilhando estado. Se executados individualmente eles passam, se executarem em conjunto eles falham. 