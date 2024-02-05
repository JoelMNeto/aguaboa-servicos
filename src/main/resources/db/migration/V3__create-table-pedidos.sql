create type status as enum ('PAGO', 'EM ABERTO');

create type tipo as enum('ENTREGA', 'RETIRADA');

create table pedidos (
	id serial primary key,
	valor_total numeric not null,
	frete numeric,
	tipo_do_pedido tipo not null,
	status status not null,
	data_de_criacao timestamp not null,
	cliente_id int not null,
	foreign key (cliente_id) references clientes(id)
);