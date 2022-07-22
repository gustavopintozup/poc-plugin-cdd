#!/bin/bash

mvn clean compile assembly:single

cp target/cdd-0.0.1-SNAPSHOT-jar-with-dependencies.jar ../stk-dev-java/cdd/cdd.jar
