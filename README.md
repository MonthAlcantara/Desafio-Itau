<div align="center">

![](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)
</div>

<div align="center">

# DESAFIO ITAÚ - CRUD SPRING BOOT - Java
O projeto tem por objetivo a realização de um crud de Clientes.

![](https://img.shields.io/badge/Autor-Montival%20Junior-brightgreen)
![](https://img.shields.io/badge/Language-Java-brightgreen)
![](https://img.shields.io/badge/Framework-Springboot-brightgreen)
![](https://img.shields.io/badge/HTTP-Restful-brightgreen)

</div> 

## Contexto

> API de cadastro de clientes da categoria PF (pessoa física). O processo foi dividido em 3 etapas para evitar necessidade de envio de um grande número de informações de uma única vez, visando a garantia do recebimento dos dados. 

## Tecnologias
- Java 11
- Spring Boot 2.5.RELEASE
    - spring-boot-starter-web
    - spring-boot-starter-data-jpa
    - spring-boot-devtools
    - bean-validation
  
- Docker
- Lombok
- Mapstruct
- Prometheus
- Grafana
- Jaeger 
- Flywaydb
- H2
- Tomcat (Embedded no Spring Boot)
- OpenApi

## Execução

Para executar o projeto é necessario adicionar as variáveis de ambiente referentes ao acesso ao banco de dados.
Para acesso a funcionalidades como Jaeger, Prometheus e Grafana, faz-se necessario execução do arquivo ```./docker-compose.yml```
A execução daa aplicação pode ser feita através de um comando Gradle que invoca a inicialização do Spring Boot.

### Variaveis de Ambiente
```
username.db.clientes=sa
password.db.clientes=Yjkdu8g4

```

### Executar a aplicação
   - 1° comando: ```docker-compose up```
   - 2° comando: ``` ./gradlew build```
   - 3° comando: ```./gradlew bootRun```


### Endereços importantes
   - H2: ```http://localhost:8080/h2```
   - Jaeger: ```http://localhost:16686/```
   - Prometheus: ``` http://localhost:9090/```
   - Grafana: ```http://localhost:3000/```
   - Swagger: ```http://localhost:8080/swagger-ui/index.html```

## Utilização
- Collection para teste via Postman

  ``` ./collection/CRUD-Clientes Itau.postman_collection.json```

### Teste via Curl
 
- POST
````
curl --location --request POST 'localhost:8080/v1/clientes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"Fulano da Silva",
    "sobrenome":"Fulano da Silva",
    "cpf" :"13112939069",
    "email":"fulano_da_silva@email.com.br",
    "dataDeNascimento":"24/10/1990"
}'
````

- GET

```
curl --location --request GET 'localhost:8080/v1/clientes'
```
```
curl --location --request GET 'localhost:8080/v1/clientes/fulano_da_silva@email.com.br'
```
- Patch

````
curl --location --request PATCH 'http://localhost:8080/v1/clientes/1/endereco' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bairro": "Bairro A",
    "cep": "4000000",
    "cidade": "Salvador",
    "complemento": "Complemento B",
    "estado": "Bahia",
    "rua": "Rua C"
}'

````

- PUT

````
curl --location --request PUT 'localhost:8080/v1/clientes/fulano_da_silva@email.com.br' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"Fulano",
    "sobrenome":"da Silva",
    "cpf" :"13112939069",
    "email":"fulano_da_silva@email.com.br",
    "dataDeNascimento":"24/10/1990"
}'
````

- DELETE
````
curl --location --request DELETE 'localhost:8080/v1/clientes/fulano_da_silva@email.com.br'
````

 
