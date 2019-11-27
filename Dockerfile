 FROM java:8
 COPY blog-0.0.1-SNAPSHOT.jar /blog-server/simple-blog-server.jar
 EXPOSE 8072
 CMD ["java", "-jar", "/blog-server/simple-blog-server.jar"]
 RUN echo "Asia/Shanghai" > /etc/timezone
 #设置系统编码
 RUN yum install kde-l10n-Chinese -y
 RUN yum install glibc-common -y
 RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
 #RUN export LANG=zh_CN.UTF-8
 #RUN echo "export LANG=zh_CN.UTF-8" >> /etc/locale.conf
 #ENV LANG zh_CN.UTF-8
 ENV LC_ALL zh_CN.UTF-8