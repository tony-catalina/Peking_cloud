FROM java:8u72-jdk

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.framework.eureka-1.0-SNAPSHOT.jar /app/

CMD ["java","-Xms200m","-Xmx300m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=300m",\
	"-XX:MaxMetaspaceSize=300m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar", \
	"/app/awd.cloud.framework.eureka-1.0-SNAPSHOT.jar", \
	"--eureka.serviceUrl=http://192.168.4.30:3001/eureka/,http://192.168.4.30:3002/eureka/,http://192.168.4.30:3003/eureka/", \
	"--hostname=eureka", \
	"--port=3001"]

#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3001/health || exit 1

EXPOSE 3001