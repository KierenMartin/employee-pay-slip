# First Stage
FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD

COPY ./ ./

# Explicitly copy pom.xml, to solve an issue
COPY pom.xml pom.xml

# Now guaranteed to contain pom.xml, should work?
RUN mvn clean package

# Second stage
FROM openjdk:8-jre-alpine3.9

COPY --from=MAVEN_BUILD target/employee-pay-slip-1.jar /program.jar

CMD ["java", "-jar", "/program.jar"]
