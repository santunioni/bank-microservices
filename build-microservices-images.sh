cd microservices/transaction-app
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native-distroless -t quarkus/transfer-app .
docker run -i --rm -p 8080:8080 -d quarkus/transfer-app
cd ../..

cd microservices/balance-app
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native-distroless -t quarkus/balance-app .
docker run -i --rm -p 8079:8080 -d quarkus/balance-app
cd ../..
