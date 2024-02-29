create table enderecos (
	id serial primary key,
	logradouro varchar(255) not null,
	numero varchar(20) not null,
	complemento varchar(255),
	bairro varchar(255),
	cep varchar(20),
	cidade varchar(100),
    ativo boolean,
	data_de_criacao timestamp not null
);