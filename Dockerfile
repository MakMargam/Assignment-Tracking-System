# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM maven:3.8.5-openjdk-8 AS build

WORKDIR /home/app

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package
RUN ls /home/app/target

FROM openjdk:8-jre-slim

COPY --from=build /home/app/target/ats-1.0-SNAPSHOT.jar /usr/local/lib/ats-1.0-SNAPSHOT.jar
EXPOSE 8000
entrypoint ["java", "-jar", "/usr/local/lib/ats-1.0-SNAPSHOT.jar"]