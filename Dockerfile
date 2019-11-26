 FROM java:8
 COPY blog-0.0.1-SNAPSHOT.jar /blog-server/simple-blog-server.jar
 EXPOSE 8072
 CMD ["java", "-jar", "/blog-server/simple-blog-server.jar"]
 ENV LANG en_US.UTF-8
 RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
 RUN /bin/echo -e "ZONE="Asia/Shanghai"\nUTC=false\nRTC=false" > /etc/sysconfig/clock