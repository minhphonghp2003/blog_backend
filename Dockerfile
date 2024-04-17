FROM eclipse-temurin:21.0.1_12-jdk

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod 777 mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
CMD [ "./mvnw","spring-boot:run"]