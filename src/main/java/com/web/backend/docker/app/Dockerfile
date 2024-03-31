FROM adoptopenjdk/openjdk8

VOLUME /tmp

ADD app.jar app.jar

ADD entrypoint.sh entrypoint.sh

RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "sh", "./entrypoint.sh" ]