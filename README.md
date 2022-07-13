# Plugin do CDD

Essa é uma POC de um plugin que calcula os ICPs do CDD usando a ferramenta de análise estática [spoon](https://github.com/INRIA/spoon).

## ICPs

Atualmente a POC computa os seguintes ICPs:

- [x] METHOD("method")
- [X] ANNOTATION("annotation")
- [X] ANNOTATION_METHOD("annotation_method")
- [X] IF_STATEMENT("if_statement")
- [X] TRY_STATEMENT("try_statement")
- [X] CATCH_SECTION("catch_section")
- [X] THROW_STATEMENT("throw_statement")
- [X] WHILE_STATEMENT("while_statement")
- [X] FOR_STATEMENT("for_statement")
- [X] FOREACH_STATEMENT("foreach_statement")
- [X] LAMBDA_EXPRESSION("lambda_expression")
- [X] SWITCH_EXPRESSION("switch_expression")
- [X] YIELD_STATEMENT("yield_statement")
- [X] SUPER_EXPRESSION("super_expression")
- [X] ANONYMOUS_CLASS("anonymous_class")
- [X] LOCAL_VARIABLE("local_variable")
- [ ] TYPE_CAST_EXPRESSION("type_cast_expression")
- [ ] IMPLICIT_VARIABLE("implicit_variable")
- [ ] IMPORT_STATIC_REFERENCE_ELEMENT("import_static_reference_element")
- [ ] C_LASS_INITIALIZER("c_lass_initializer"), 
- [ ] CLASS("class")
- [ ] METHOD_CALL_EXPRESSION("method_call_expression")


## Observações

Nesse momento, *annotation* e *annotation method* estão sendo calculados no mesmo momento (não vi sentido em separa-los).

## Duvidas
- Como identificar um static import
- Como identificar um cast
- Preciso contabilizar if/elses do equals()? --> preciso parametrizar essa informação
- Anotações/definições de variáveis repetidas contam mais de uma vez? --> contam somente uma única vez

## Todo
- Calcular os pesos dos ICPs no prettyprint()