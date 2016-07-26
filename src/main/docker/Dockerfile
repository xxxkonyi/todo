# Use this image as a base
FROM pierrevincent/gradle-java8
MAINTAINER konyi <xxxkonyi@gmail.com>

VOLUME /tmp

ADD / /tmp/build

#构建应用
RUN cd /tmp/build && gradle build \
        #拷贝编译结果到指定目录
        && mv build/libs/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

#RUN bash -c 'touch /app.jar'

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]