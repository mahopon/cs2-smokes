FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/cs2-smokes-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 18080

CMD ["java", "-jar", "app.jar"]