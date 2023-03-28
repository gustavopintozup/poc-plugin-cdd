build: 
	make clean
	mvn compile assembly:single

run: 
	make build
	java -jar target/cdd-0.0.2-SNAPSHOT-jar-with-dependencies.jar -p ~/workspace/jackson-dataformat-xml -o JSON

clean:
	mvn clean
	rm -rf spooned
