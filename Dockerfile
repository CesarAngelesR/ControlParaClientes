FROM adoptopenjdk/openjdk11:alpine-jre
COPY "./target/ControlParaClientes-0.0.1-SNAPSHOT.jar" "app.jar"

COPY "./src/main/resources/templates/" "/app/templates"

EXPOSE 8099

ENTRYPOINT ["java","-jar","app.jar"]