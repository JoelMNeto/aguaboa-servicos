create table pedidos (
	id serial primary key,
	valor_total numeric not null,
	frete numeric,
	tipo_do_pedido varchar(100) not null,
	status varchar(100) not null,
	data_de_criacao timestamp not null,
	cliente_id int not null,
    forma_pagamento varchar(255),
    valor_pago numeric,
    valor_atualizado numeric,
    ativo boolean,
	foreign key (cliente_id) references clientes(id)
);