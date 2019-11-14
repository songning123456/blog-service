 FROM java:8
 COPY blog-0.0.1-SNAPSHOT.jar /blog-server/simple-blog-server.jar
 EXPOSE 8072
 CMD ["java", "-jar", "/blog-server/simple-blog-server.jar"]