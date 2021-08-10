
SET SQL_SAFE_UPDATES = 0;

drop database if exists dbLoja;

create database dbLoja;

use dbLoja;

create table clientes(
	idCliente int not null auto_increment primary key,
    nomeCliente varchar(45) not null,
    cpf varchar(14) not null,
    telefone varchar(45) not null,
    endereco varchar(100) not null
);

create table categorias (
	idCategoria int not null auto_increment primary key,
    nomeCategoria varchar(70) not null
);

create table entregadores (
	idEntregador int not null auto_increment primary key,
    nomeEntregador varchar(70) not null
);

create table produtos(
	idProduto int not null auto_increment primary key,
    idCategoria int not null,
    nomeProduto varchar(70) not null,
    valor float not null,
    constraint fk_produtos_categorias foreign key (idCategoria) references categorias (idCategoria)
);

create table encomendas(
	idEncomenda int not null auto_increment primary key,
    idCliente int not null,
    idEntregador int not null,
    endereco varchar(100) not null,
    dataEntrega date not null,
    horaEntrega time not null,
    statusEnc ENUM('0', '1', '2', '3') NOT NULL DEFAULT '0' COMMENT '0 => \'Em produção\n1 => \'Separado para entrega\'\n2 => \'Em transporte\'\n3 => \'Entrega finalizada\'',
    ativo enum ('0', '1') not null default '1' comment '0 => inativo, 1 => ativo',
    constraint fk_encomendas_clientes foreign key (idCliente) references clientes (idCliente),
	constraint fk_encomendas_entregadores foreign key (idEntregador) references entregadores (idEntregador)

);

CREATE TABLE produtos_encomendas (
  idProdutos_Encomendas INT NOT NULL AUTO_INCREMENT primary key,
  idProduto INT(11) NOT NULL,
  idEncomenda INT(11) NOT NULL,
  quantidade INT NOT NULL,
   CONSTRAINT fk_produtos_has_encomendas_produtos1
    FOREIGN KEY (idProduto)
    REFERENCES produtos (idProduto),
  CONSTRAINT fk_produtos_has_encomendas_encomendas1
    FOREIGN KEY (idEncomenda)
    REFERENCES encomendas (idEncomenda)
  );

insert into clientes (nomeCliente, cpf, telefone, endereco) values ('José', '070.996.123-08', '1235546', 'Rua das Baleias franca, 341, Jurerê, Florianóplis');
insert into clientes (nomeCliente, cpf, telefone, endereco) values ('Ana', '088.343.965-99', '854322334', 'Rua Silva Jardim, 360, Centro, Florianópolis');
insert into clientes (nomeCliente, cpf, telefone, endereco) values ('Carolina', '667.832.911-00', '987742', 'Rua John Lennon, 300, Areias, São José');
insert into clientes (nomeCliente, cpf, telefone, endereco) values ('Bruno', '092.845.992-09', '1654656', 'Avenida Atlântica, 2000, Balneário Camboriú');
insert into clientes (nomeCliente, cpf, telefone, endereco) values ('Andressa', '991.233.644-99', '6546546', 'Rua 239, 39, Meia Praia');
insert into clientes (nomeCliente, cpf, telefone, endereco) values ('Eduardo', '994.645.766-57', '3343554', 'Rua Antônio dAvila, 111, Jardim Cibeli, Araranguá');
select * from clientes order by clientes.nomeCliente;
delete from entregadores;

insert into categorias (nomeCategoria) values ('Doce');
insert into categorias (nomeCategoria) values ('Salgado');
insert into categorias (nomeCategoria) values ('Refrigerante');
insert into categorias (nomeCategoria) values ('Assados');
insert into categorias (nomeCategoria) values ('Fritos');
select * from categorias;

insert into produtos (idCategoria, nomeProduto, valor) values (2, 'Coxinha', 5);
insert into produtos (idCategoria, nomeProduto, valor) values (4, 'Empada', 7);
insert into produtos (idCategoria, nomeProduto, valor) values (1, 'Bolo', 15);
insert into produtos (idCategoria, nomeProduto, valor) values (3, 'Coca-cola', 6);
insert into produtos (idCategoria, nomeProduto, valor) values (5, 'Pastel', 6);
select * from produtos 
inner join categorias on categorias.idCategoria = produtos.idCategoria
order by produtos.nomeProduto; 

insert into entregadores(nomeEntregador) VALUES ('João');
insert into entregadores(nomeEntregador) VALUES ('Daniel');
insert into entregadores(nomeEntregador) VALUES ('Paulo');
insert into entregadores(nomeEntregador) VALUES ('Mateus');
insert into entregadores(nomeEntregador) VALUES ('Yuri');
insert into entregadores(nomeEntregador) VALUES ('Sarah');
select * from entregadores;


insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (1, 2, 'Rua das Baleias franca, 341, jurerê', '2018-10-25 ', '10:30:00', 1);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (2, 1, 'Av. dos dourados, 112, jurerê', '2018-10-28', '10:30:00' ,2);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (2, 1, 'Av. dos búzios, 20, jurerê', '2018-10-28','10:30:00' , 3);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (2, 2, 'Av. das lagostas, 1120, jurerê', '2018-10-19','10:30:00' , 4);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (3, 1, 'Av. dos salmões, 63, jurerê', '2018-10-23','10:30:00' , 2);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (3, 2, 'Rua das tainhas, 192, jurerê', '2018-12-13','10:30:00' , 3);
insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc) values (3, 2, 'Rua das tainhas, 192, jurerê', '2018-11-10','10:30:00' , 3);
update encomendas set ativo = '0' where idEncomenda = 14;
select encomendas.idEncomenda ,clientes.nomeCliente
, encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc, encomendas.ativo from encomendas 
inner join clientes on clientes.idCliente = encomendas.idCliente
inner join entregadores on entregadores.idEntregador = encomendas.idEntregador
where not statusEnc = '4' and not encomendas.ativo = 1
order by encomendas.dataEntrega; 
select max(encomendas.idEncomenda) from encomendas;

insert into produtos_encomendas(idProduto, idEncomenda, quantidade) values (2, 3, 150);
insert into produtos_encomendas(idProduto, idEncomenda, quantidade) values (4, 3, 150);

select produtos_encomendas.idProdutos_Encomendas, produtos.nomeProduto, 
produtos_encomendas.quantidade, produtos.valor, encomendas.idEncomenda 
from produtos_encomendas
inner join produtos on produtos.idProduto = produtos_encomendas.idProduto
inner join encomendas on encomendas.idEncomenda = produtos_encomendas.idEncomenda;