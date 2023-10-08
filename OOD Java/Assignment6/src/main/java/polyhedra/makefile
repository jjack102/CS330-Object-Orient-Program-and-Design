# Gradle Wrapper Makefile

ASSIGNMENT=CreatePolyhedra

all:
	chmod a+rx gradlew
	./gradlew test jar

test:
	./gradlew test

retroguard:
	./gradlew jar
	cp  build/libs/$(ASSIGNMENT).jar temp.jar
	java -cp .:/home/zeil/src/retroguard/retroguard.jar RetroGuard temp.jar $(ASSIGNMENT)-obfuscated.jar
	rm temp.jar

run: $(ASSIGNMENT).jar
	chmod a+rx gradlew
	./gradlew run

clean:
	chmod a+rx gradlew
	./gradlew clean


gradingNoRename:
	unzip polyhedra_java_1.zip
	chmod -R a+rw src
	cp Cylinder.java src/main/java/polyhedra/
	chmod a+rx gradlew
	./gradlew --no-daemon test jar

zip:
	-rm -f polyhedra_java_1.zip
	zip -9 -r polyhedra_java_1.zip gradlew gradlew.bat src gradle build.gradle gradle.properties



