build:
	./gradlew shadowJar

run: build db
	WIRE_API_HOST=https://staging-nginz-https.zinfra.io java -Djava.library.path=/Users/lukas/work/wire/cryptobox4j/build/lib \
		-jar build/libs/bots-simulation-fw-0.0.1-all.jar server simulation.yaml

db:
	docker-compose up -d db
