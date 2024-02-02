create table enderecos (
	id serial primary key,
	logradouro varchar(255) not null,
	numero varchar(20) not null,
	bairro varchar(255),
	cep varchar(9),
	cidade varchar(100),
	data_de_criacao timestamp not null
);