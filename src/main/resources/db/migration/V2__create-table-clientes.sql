create table clientes (
	id serial primary key,
	endereco_id int not null,
	nome varchar(255) not null,
	contato varchar(20) not null,
	saldo_em_conta numeric,
	ativo boolean not null, 
	data_de_criacao timestamp not null,
	foreign key (endereco_id) references enderecos(id)
);