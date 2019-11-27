 FROM java:8
 COPY blog-0.0.1-SNAPSHOT.jar /blog-server/simple-blog-server.jar
 EXPOSE 8072
 CMD ["java", "-jar", "/blog-server/simple-blog-server.jar"]
 RUN echo "Asia/Shanghai" > /etc/timezone
 ENV LANG en_US.UTF-8
 ENV LANGUAGE en_US:en
 ENV LC_ALL en_US.UTF-8