DROP DATABASE IF EXISTS details;
 
CREATE DATABASE details DEFAULT CHARACTER SET 'utf8';
 
USE details;
 
create table groups
(
  group_id int unsigned not null auto_increment,
  groupName varchar(255) not null,
  primary key (group_id)
) engine=InnoDB;
 
create table items
(
  item_id int unsigned not null auto_increment,
  itemName varchar(255) not null,
  changeDate date not null,
  group_id int not null,
  in_stock int not null,
  sold int not null,
  primary key (item_id)
) engine=InnoDB;
 
set names 'utf8';
 
insert into groups (groupName) 
values ('Первая');
insert into groups (groupName) 
values ('Вторая');
 
insert into items (itemName, changeDate, group_id, in_stock, sold)
values ('Деталь 1', '1990-03-20', 1, 10, 150);
 
 insert into items (itemName, changeDate, group_id, in_stock, sold)
values ('Деталь 2', '1990-03-20', 1, 13, 245);
