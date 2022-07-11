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
- [X] SWITCH_STATEMENS("switch_statemens") 
- [X] YIELD_STATEMENT("yield_statement")
- [X] SUPER_EXPRESSION("super_expression")
- [X] ANONYMOUS_CLASS("anonymous_class")
- [X] LOCAL_VARIABLE("local_variable")
- [ ] TYPE_CAST_EXPRESSION("type_cast_expression")
- [ ] IMPLICIT_VARIABLE("implicit_variable")
- [ ] C_LASS_INITIALIZER("c_lass_initializer"), 
- [ ] CLASS("class")
- [ ] METHOD_CALL_EXPRESSION("method_call_expression")
- [ ] IMPORT_STATIC_REFERENCE_ELEMENT("import_static_reference_element")


## Observações

Nesse momento, *annotation* e *annotation method* estão sendo calculados no mesmo momento (não vi sentido em separa-los).

## Duvidas
- Qual a diferença entre switch expressions e statements?
- Confirmar o calculo do if
- Preciso calcular o numero de classes?
- Exemplo de uso de classes anonimas
- Sem código para testar o super