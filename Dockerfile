FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/NotesApp-1.0-SNAPSHOT.jar NotesApp.jar
ENV TZ=Russia/Samara
ENTRYPOINT ["java", "-jar", "NotesApp.jar"]