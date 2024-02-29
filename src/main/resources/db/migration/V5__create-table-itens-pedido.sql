create table itens_pedido (
	id serial primary key,
	preco_unitario numeric not null,
	quantidade int not null,
	desconto numeric,
	pedido_id int not null,
	produto_id int not null,
    ativo boolean,
	foreign key (pedido_id) references pedidos(id),
	foreign key (produto_id) references produtos(id)
);