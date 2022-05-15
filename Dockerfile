# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM maven:3.8.5-openjdk-8

WORKDIR /home/app

COPY src ./src
COPY pom.xml .

RUN mvn clean package
RUN ls