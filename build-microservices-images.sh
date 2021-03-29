## NATIVE transaction-app IMAGE ##
# cd microservices/transaction-app
# ./mvnw clean package -Pnative
# docker build -f src/main/docker/Dockerfile.native-distroless -t quarkus/transfer-app .
# docker run --name transaction-app --rm -i -p 8080:8080 -d quarkus/transfer-app
# cd ../..

## JVM transaction-app IMAGE ##
cd microservices/transaction-app
./mvnw clean package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/transfer-app-jvm .
docker run -i --name transaction-app --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" quarkus/transfer-app-jvm -d
cd ../..



# cd microservices/balance-app
# ./mvnw package -Pnative
# docker run --name bankaccount-balance-app -i --rm -p 8079:8080 -d quarkus/balance-app
# docker run -i --rm -p 8079:8080 -d quarkus/balance-app
# cd ../..
