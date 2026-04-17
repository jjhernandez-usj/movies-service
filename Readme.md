Release steps:
./gradlew build     
docker build -t anselm82/music-api:latest .
docker push anselm82/music-api:latest