# Cognitive-Driven Development (CDD)

Cognitive-Driven Development (CDD) is a coding design technique that aims to reduce the effort that developers place in understanding a given piece of code, for instance, a class. 

The ideia behind CDD is that one needs to limit the number of code constructs, but this limit should be defined in a disciplined way. 

To know more about CDD, read [here](https://www.zup.com.br/blog/cognitive-driven-development-cdd) (in pt-br).

## Plugin

> **Warning**
>
> _This plugin is still under development, and should be used with care._

This implementation is built using [spoon](https://github.com/INRIA/spoon), a static analysis tool for Java programs. Currently the compliance version is set to Java 17.


## Instalation 

There are two ways to use this plugin: standalone or through StackSpot.

### Running through CLI

To use the latest version, clone this project and create a `jar` file. Then run the `jar` pointing to the project to be analyzed. For instance:

```
git clone git@github.com:gustavopintozup/poc-plugin-cdd.git

cd poc-plugin-cdd

mvn clean compile assembly:single

java -jar target/cdd-<versao-atual>-SNAPSHOT-jar-with-dependencies.jar -p <diretório-do-projeto-que-quero-analisar>
```

#### CLI Commands 

The pluggin currently supports the following commands.

| Command             | Description                                       |  Options                         |
|---------------------|---------------------------------------------------|----------------------------------|
| -p (or --path)      | Path to the project to be analyzed                |                                  |
| -f (or --full)      | List the full analysis for all existing classes   |                                  |
| -o (or --output)    | Format of the output                              | TXT, JSON or HTML                |


### Running through StackSpot

To use the CDD plugin in a more straightforward way, we recommend using the [StackSpot implementation](https://github.com/gustavopintozup/plugin-cdd-java).

## Configuring the plugin

Read [our reference documentation](config.md) to know more about how to use the plugin.

## Academic Usage

If you are using this plugin for research purposes, please cite as the following:

```
@manual{plugin-cdd,
  title={Plugin for CDD Analysis},
  author={Gustavo Pinto and Alberto de Souza},
  year={2022},
  note={Available in https://github.com/gustavopintozup/poc-plugin-cdd}
}
```

## License

This software is licensed under the MIT License.