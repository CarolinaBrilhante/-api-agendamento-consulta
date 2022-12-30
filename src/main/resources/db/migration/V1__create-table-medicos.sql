CREATE TABLE medicos(
id bigInt not null auto_increment,
nome varchar(108) not null,
email varchar(108) not null unique,
crm varchar(6) not null unique,
especialidade varchar(100) not null,
logradouro varchar(180) not null,
bairro varchar(100) not null,
cep varchar(9) not null,
complemento varchar(100),
numero varchar(20),
cidade varchar(60) not null,
uf varchar(2) not null,

primary key(id)

);