From anapsix/alpine-java

VOLUME /tmp
ADD /target/twitter-client-auth.jar startup.jar
RUN sh -c 'touch /startup.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/;/urandom","-jar","/startup.jar"]

