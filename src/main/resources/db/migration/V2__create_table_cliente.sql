CREATE TABLE cliente(
    id bigint not null auto_increment,
    email varchar(50) not null unique,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    cpf varchar(11) not null unique,
    documento varchar(255),
    data_de_nascimento varchar(20) not null,
    endereco_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(endereco_id) REFERENCES endereco (id)
);
