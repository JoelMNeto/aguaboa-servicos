create table produtos (
	id serial primary key,
	nome varchar(255) not null,
	marca varchar(255),
	preco numeric not null,
	ativo boolean not null,
	data_de_criacao timestamp not null
);