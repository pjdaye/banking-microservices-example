FROM openjdk/8-jre-slim
VOLUME /tmp
ADD build/libs/edgeservice-0.0.1.jar app.jar
ENV SPRING_PROFILES_ACTIVE=dev
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
