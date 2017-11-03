FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD profilizer-service-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom -noverify ", "-Dspring.profiles.active=container", "-Dapp.port=${app.port}", "-jar","/app.jar"]