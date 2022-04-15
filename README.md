### Actuator module
```groovy
implementation ('org.springframework.boot:spring-boot-starter-actuator')
```   
actuator endpoint
- /application/autoconfig
- /application/beans
- /application/configprops
- /application/dump
- /applicaiton/env
- /application/health
- /application/info
- /application/metrics
- /application/mappings
- /application/trace   
엔드포인트는 기본적으로 비활성화되어 있다. 활성화를 위해서는 프로퍼티 파일내에 정의해서 활성화 시켜야 한다.    
```yaml
endpoints:
   health:
     enabled: true
   metrics:
     enabled: true
```   
http://localhost:8080/actuator/health 을 통해서 확인할 수 있다. 
### build
```shell
$ ./gradlew clean build
```

### Netty 기반을 Tomcat 기반으로 전환하려면
```groovy
implementation('org.springframework.boot:spring-boot-starter-webflux') {
    exclude group: 'org.springframework.boot',
    moduel: 'spring-boot-starter-reactor-netty'
}
implementation('org.springframework.boot:spring-boot-starter-tomcat')
```