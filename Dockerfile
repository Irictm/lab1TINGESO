FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} lab1-tingeso-backend.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "./lab1-tingeso-backend.jar"]