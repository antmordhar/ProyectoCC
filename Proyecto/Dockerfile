
FROM maven AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package

FROM adoptopenjdk/openjdk8
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/target/RestauranProject-0.0.1-SNAPSHOT.jar