#Stage 1: t build the application
FROM eclipse-temurin:21-jdk-jammy AS builder

#Set the working directory inside the container
WORKDIR /app

#Install the Apache Maven build tool
#Update package lists and install Maven without recommended package to keep the layer small
RUN apt-get update && apt-get install -y --no-install-recommend maven && re -rf /var/lib/apt/lists/*

COPY pom.xml

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -Dmaven.test.skip=true




#Stage 2: is to build a production ready image nad run

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

#Copy the final executable JAR from the 'builder' stage's target directory
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8090

#Define the command to run the plication when the container starts
ENTRYPOINT ["java","-jar","app.jar"]