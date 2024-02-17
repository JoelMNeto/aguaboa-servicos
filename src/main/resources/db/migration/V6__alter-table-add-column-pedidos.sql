alter table pedidos
    add column forma_pagamento varchar(255),
    add column valor_pago numeric,
    add column valor_atualizado numeric,
    add column ativo boolean;

alter table itens_pedido add column ativo boolean;

alter table enderecos add column ativo boolean;
