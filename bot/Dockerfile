FROM adoptopenjdk/openjdk11
COPY ./target/bot-0.0.1-SNAPSHOT.jar /app/myapp.jar
WORKDIR /app
EXPOSE 8082
ENV PROFILE="default"
CMD ["java", "-jar","-Dspring.profiles.active=${PROFILE}", "myapp.jar"]
