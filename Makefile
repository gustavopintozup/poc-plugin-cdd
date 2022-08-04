build: 
	make clean
	mvn compile assembly:single

update-stk:
	cp README.md ~/workspace/plugin-cdd-java/documentacao-plugin-cdd.md
	cp target/cdd-0.0.2-SNAPSHOT-jar-with-dependencies.jar ~/workspace/plugin-cdd-java/cdd.jar
	cp cdd.json ~/workspace/plugin-cdd-java/

run: 
	make build
	java -jar target/cdd-0.0.2-SNAPSHOT-jar-with-dependencies.jar -p ../plataforma-treino-lms/ -o json

clean:
	mvn clean
	rm -rf spooned
