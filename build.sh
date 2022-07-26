#!/bin/bash

mvn clean compile assembly:single

cp target/cdd-0.0.1-SNAPSHOT-jar-with-dependencies.jar ~/workspace/plugin-cdd-java/cdd.jar

cp README.md ~/workspace/plugin-cdd-java/documentacao-plugin-cdd.md

cp cdd.json ~/workspace/plugin-cdd-java/