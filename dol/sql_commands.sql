set client_encoding='WIN1251';

CREATE DATABASE dolbase;
\c dolbase


psql \! chcp 1251
;
;
;
drop table contracts;
drop table sets;
drop table users;
drop table statuses;


create table users(
user_id sERIAL PRIMARY KEY,
login            varchar(30)  not null,
 pass            bytea not null
);

create table statuses(
id   SERIAL PRIMARY KEY,
name varchar(20) not null
);




create table contracts (
contract_ID   SERIAL PRIMARY KEY,
userLogin varchar(50) not null,
passportID varchar(10) not null,
statusNumber integer  not null,
setNumber integer not null,
date date not null,
scan bytea
);




create table sets(
id   SERIAL PRIMARY KEY,
name varchar(20) not null,
free boolean  not null,
ctn int not null,
plan varchar(40) not null,
dealerName varchar(15) not null
);

insert into sets(name,free,ctn,plan,dealerName) values('name1',true,'11','plan1', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name2',true,'12','plan2', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name3',true,'13','plan3', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name4',true,'14','plan4', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name5',true,'15','plan1', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name6',true,'16','plan2', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name7',true,'17','plan3', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name8',true,'18','plan4', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name9',true,'19','plan5', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name10',true,'20','plan1', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name11',true,'21','plan1', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name12',true,'21','plan1', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name2',true,'12','plan2', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name3',true,'13','plan3', 'Ivan');
insert into sets(name,free,ctn,plan,dealerName) values('name4',true,'14','plan4', 'Ivan');
insert into sets(name,free,ctn,plan,dealerName) values('name5',false,'15','plan5', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name6',false,'16','plan6', 'Max');
insert into sets(name,free,ctn,plan,dealerName) values('name7',false,'17','plan7', 'Ivan');
insert into sets(name,free,ctn,plan,dealerName) values('name8',false,'18','plan8', 'Ivan');




insert into users (login,pass) values('Ivan','\x8978d00cb2cd13ec8f5a8e94adb51037');
insert into users (login,pass) values('Max','\x104bc94e9848863de7e626cb443e9f75'); 

insert into statuses(name) values('�����');
insert into statuses(name) values('������ ��������');
