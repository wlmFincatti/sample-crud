echo "--------- start process ---------"

cd ./bot && mvn clean install -DskipTests=true

cd ../message && mvn clean install -DskipTests=true

cd ../ && docker-compose -f localstack.yaml up -d
