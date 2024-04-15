FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=build/libs/modulith-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Xms512m","-Xmx512m","/app.jar"]