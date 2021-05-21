--drop table stock;
--drop table products;
-- create schema store;

/* create table Foo (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   PRIMARY KEY (id)
); */

create table Userorder (
   id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(200) NOT NULL,
   price float NOT NULL,
   quantity int,
   product_id int not null,
   PRIMARY KEY (id)
);

/* create table Stock (
   id INT NOT NULL AUTO_INCREMENT,
	product_id int references Product(ID),
	count int not null,
   PRIMARY key (id)
); */