create table cliente(
nome varchar(100),
telefone varchar(20),
email varchar(100),
id_cliente int auto_increment primary key);


create table financeiro (
id_cliente int,
id_financeiro int auto_increment primary key,
valor numeric(10,2),
tipo varchar(100),
forma_pagamento varchar(100),
data_movimento date,
historico varchar(1000),
situacao varchar(100)
);
