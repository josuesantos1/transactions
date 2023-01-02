FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/transactions-0.0.1-SNAPSHOT-standalone.jar /transactions/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/transactions/app.jar"]
