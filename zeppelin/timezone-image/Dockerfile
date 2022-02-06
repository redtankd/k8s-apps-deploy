FROM apache/zeppelin:0.10.0

USER root

RUN apt-get update && apt-get install -y vim tzdata && apt-get clean

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

USER 1000