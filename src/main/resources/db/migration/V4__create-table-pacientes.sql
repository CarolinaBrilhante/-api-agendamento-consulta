CREATE TABLE pacientes(
id bigInt not null auto_increment,
nome varchar(108) not null,
email varchar(108) not null unique,
telefone varchar(20) not null,
cpf varchar(11) not null unique,
logradouro varchar(180) not null,
bairro varchar(100) not null,
cep varchar(9) not null,
complemento varchar(100),
numero varchar(20),
cidade varchar(60) not null,
uf varchar(2) not null,

primary key(id)

);