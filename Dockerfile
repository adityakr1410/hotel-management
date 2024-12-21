FROM maven:3.8.5-openjdk-21 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/hotelManagement-0.0.1-SNAPSHOT.jar hotelManagement.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","hotelManagement.jar" ]
