FROM openjdk:17-alpine3.14

LABEL maintainer="nekoimi <nekoimime@gmail.com>"

ARG JAR_NAME="target/course-login-1.0-SNAPSHOT.jar"

ENV TZ Asia/Shanghai
ENV PROFILE dev
ENV JAVA_OPS -server -Xms128m -Xmx512m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

WORKDIR workspace

COPY ${JAR_NAME}  app.jar

EXPOSE 9000

CMD java ${JAVA_OPS} -Dspring.profiles.active=${PROFILE} -Djava.security.egd=file:/dev/./urandom -jar app.jar

