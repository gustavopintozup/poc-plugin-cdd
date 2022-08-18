
# Configuring the plugin 

The plugin entry point is the `cdd.json` file, that should be placed in the root dir of the project under analysis.

In the `cdd.json` file is the place you shoud list the ICPs you would like to compute. [The list of supported ICPs are listed here](ICPs).

An example configuration file can be seen below:

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