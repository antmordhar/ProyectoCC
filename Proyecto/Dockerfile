
FROM maven AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM adoptopenjdk/openjdk8
VOLUME /tmp/
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY ./target/RestauranProject-0.0.1-SNAPSHOT.jar restaurant.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar restaurant.jar
