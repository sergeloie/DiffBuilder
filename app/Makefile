run-dist:
	@./build/install/app/bin/app testFile1.json testFile2.json

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

.PHONY: build
