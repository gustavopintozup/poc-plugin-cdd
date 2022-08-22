build: 
	make clean
	mvn compile assembly:single

update-stk:
	cp ICPs.md ~/workspace/plugin-cdd-java/docs/ICPs.md
	cp config.md ~/workspace/plugin-cdd-java/docs/config.md
	
	cp target/cdd-0.0.2-SNAPSHOT-jar-with-dependencies.jar ~/workspace/plugin-cdd-java/cdd.jar
	cp cdd.json ~/workspace/plugin-cdd-java/

run: 
	make build
	java -jar target/cdd-0.0.2-SNAPSHOT-jar-with-dependencies.jar -p ~/workspace/jackson-dataformat-xml -o JSON

clean:
	mvn clean
	rm -rf spooned
