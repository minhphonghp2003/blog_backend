FROM eclipse-temurin:21.0.1_12-jdk

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD [ "./mvnw","spring-boot:run","-Djwt_secret=$jwt_secret -DDB_PASS=$DB_PASS -DMONGO_USERNAME=$MONGO_USERNAME -DMONGO_PASS=$MONGO_PASS -DMONGO_CLUSTER=$MONGO_CLUSTER -DMONGO_DB=$MONGO_DB -Dbonsai_url=$bonsai_url"]