create table clientes (
	id serial primary key,
	nome varchar(255) not null,
	celular varchar(15),
	telefone varchar(15) not null,
	saldo_em_conta numeric,
	ativo boolean not null,
	data_de_criacao timestamp not null,
	endereco_id int not null,
	foreign key (endereco_id) references enderecos(id) 
);