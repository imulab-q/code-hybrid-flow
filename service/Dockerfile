FROM openjdk:8-jre-alpine3.9

ADD build/libs/code-hybrid-flow-service-*-fat.jar code-hybrid-flow-service.jar

ENTRYPOINT ["java", "-jar", "-noverify", "code-hybrid-flow-service.jar"]