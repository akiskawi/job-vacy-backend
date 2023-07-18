# Download Docker First!
[Download docker for Windows!](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)


#### Create the Containers / Run app

```shell
./mvnw clean package -DskipTests
docker-compose up
```
#### Remove the Containers
```shell
docker-compose down
```