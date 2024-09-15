#Build stage
FROM gradle:latest AS BUILD
WORKDIR /app
COPY . /app/.
RUN gradle build

# Package stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=BUILD /app/build/libs/*.jar /app/bank.jar
EXPOSE 8080
ENTRYPOINT exec java -jar /app/bank.jar
