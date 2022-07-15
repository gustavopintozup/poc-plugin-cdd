# Plugin do CDD

Essa é uma POC de um plugin que calcula os ICPs do CDD usando a ferramenta de análise estática [spoon](https://github.com/INRIA/spoon).

## ICPs

Atualmente a POC computa os seguintes ICPs:

- [x] METHOD: Contabiliza o número de métodos de uma classe
- [X] ANNOTATION: Contabiliza o número de anotações de uma classe
- [X] IF_STATEMENT: Contabiliza o número de `if` e `else` que em uma classe. Também contabilizam o uso de operadores ternários.
- [X] SWITCH_EXPRESSION: Contabiliza o numero de `cases` em cada `switch` de uma classe.
- [X] TRY_STATEMENT: Contabiliza o número de `try` e `finally` em uma calsse
- [X] CATCH_SECTION: Contabiliza a quantidade de `catch` em nos `try`s de uma classe; 
- [X] THROW_STATEMENT: Contabiliza a quantidade de `throw` nos  `try`s de uma classe;
- [X] WHILE_STATEMENT: Contabiliza a quantidade de laços do tipo `while` em uma classe;
- [X] FOR_STATEMENT: Contabiliza a quantidade de laços do tipo `for` em uma classe;
- [X] FOREACH_STATEMENT: Contabiliza a quantidade de laços do tipo `foreach` em uma classe;
- [X] LAMBDA_EXPRESSION: Contabiliza a quantidade de expressões `lambda` em uma classe;
- [X] YIELD_STATEMENT: Contabiliza a quantidade de `yield` em `switch` de uma classe;
- [X] SUPER_EXPRESSION: Contabiliza a quantidade de chamadas ao `super` em uma classe; 
- [X] ANONYMOUS_CLASS: Contabiliza a quantidade de uso de classes anonimas em uma classe;
- [X] LOCAL_VARIABLE: Contabiliza a quantidade de variáveis definidas no escopo de uma classe;

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
- Preciso contabilizar if/elses do equals()? --> preciso parametrizar essa informação
- Anotações/definições de variáveis repetidas contam mais de uma vez? --> contam somente uma única vez

## Todo
- Calcular os pesos dos ICPs no prettyprint()

## Problemas conhecidos 

- A contabilização de ifs não considera o `else if` (apenas `if` e `else`)
- O calculo de condições unárias não está correto
- Os testes de unidade da classe `MetricasCDDTest` estão com compartilhando estado. Se executados individualmente eles passam, se executarem em conjunto eles falham. 