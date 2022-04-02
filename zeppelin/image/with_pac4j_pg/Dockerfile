FROM apache/zeppelin:0.10.0

USER root

RUN apt-get remove -y openjdk-8-jre-headless
RUN apt-get update && apt-get install -y vim tzdata openjdk-11-jre-headless && apt-get clean

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

COPY ./pac4j/lib/* /opt/zeppelin/lib
COPY ./pg/* /opt/zeppelin/lib
RUN rm /opt/zeppelin/lib/nimbus-jose-jwt-4.41.2.jar

USER 1000