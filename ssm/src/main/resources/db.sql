create database ssm;
use ssm;
create table account(
id int primary key auto_increment ,
name varchar(20),
money double
);


insert into account(name,money) values('张三',1000);
insert into account(name,money) values('李四',2000);
insert into account(name,money) values('王五',3000);


select * from account;

