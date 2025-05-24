FROM maven:3.9.6-amazoncorretto-21-debian AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM amazoncorretto:21

ENV DB_URL=jdbc:postgresql://aguaboa-database.cdeqgay80tf8.us-east-2.rds.amazonaws.com/postgres
ENV DB_USER=postgres
ENV DB_PASSWORD={DB_PASSWORD}
ENV ADMIN_KEY={ADMIN_KEY}
ENV AWS_REGION=us-east-2

COPY --from=build /app/target/aguaboa-servicos-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 80

CMD ["java", "-jar", "app.jar"]