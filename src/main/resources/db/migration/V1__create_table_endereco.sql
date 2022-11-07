CREATE TABLE endereco(
    id bigint not null auto_increment,
    cep varchar(10) not null,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    complemento varchar(100) not null,
    cidade varchar(100) not null,
    estado varchar(50) not null,
    PRIMARY KEY(id)
);