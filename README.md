## Serviço Rest de autenticação Oauth
### (twitter-client-auth)

### Build docker
- mvn install
- docker build -t twcli-auth-docker .
- docker run --net twclientnet --ip 172.18.0.23 -d -p 8093:8093 {tag}

### Tecnologias

- Java 8 (rest)
- Maven
- Spring boot
- Swagger
- Oauth2
- Flyway

Em desenvolvimento...

Autor
Leo costa - Initial work - GitUsersFriends
