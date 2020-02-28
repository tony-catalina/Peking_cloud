FROM java:8u72-jdk

MAINTAINER njawd sgh <306165699@qq.com>

ADD ./target/awd.cloud.framework.config-1.0-SNAPSHOT.jar /app/

COPY ./config    /root/soa/config-repo


CMD ["java","-Xms300m","-Xmx550m",\
	"-XX:CompressedClassSpaceSize=128m",\
	"-XX:MetaspaceSize=500m",\
	"-XX:MaxMetaspaceSize=500m",\
	"-XX:MinHeapFreeRatio=10",\
	"-XX:MaxHeapFreeRatio=80",\
	"-Duser.timezone=GMT+8", \
	"-jar","/app/awd.cloud.framework.config-1.0-SNAPSHOT.jar", \
	"--eureka.serviceUrl=http://192.168.4.30:3001/eureka/,http://192.168.4.30:3002/eureka/,http://192.168.4.30:3003/eureka/", \
	"--hostname=awd-mis-config-server", \
	"--port=3000"]


#HEALTHCHECK --interval=10s --timeout=3s --retries=24 CMD curl -f http://localhost:3000/health || exit 1

EXPOSE 3000