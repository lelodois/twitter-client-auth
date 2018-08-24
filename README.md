## Serviço Rest client que consome api do twitter para buscar hashtags
### (twitter-client-back)

### Build docker
- mvn install
- docker build -t twcli-auth-docker .
- docker run --net mynet123 --ip 172.18.0.22 -d -p 8092:8092 {tag}

### Tecnologias

- Java 8 (rest)
- Maven
- Spring boot
- Swagger

Em desenvolvimento...

Autor
Leo costa - Initial work - GitUsersFriends
