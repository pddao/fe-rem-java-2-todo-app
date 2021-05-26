FROM openjdk:15

ENV ENVIRONMENT=prod

MAINTAINER Dung Dao <dung.dao@hotmail.de>

ADD backend/target/todo-docker.jar app.jar

CMD [ "sh", "-c", "java -jar /app.jar" ]