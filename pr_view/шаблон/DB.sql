CREATE DATABASE staff DEFAULT CHARACTER SET 'utf8';
 
USE staff;
 
create table department
(
  dep_id int unsigned not null auto_increment,
  depName varchar(255) not null,
  chief varchar(255) not null,
  amount_people int not null,
  primary key (dep_id)
) engine=InnoDB;
 
create table person
(
  person_id int unsigned not null auto_increment,
  firstName varchar(255) not null,
  surName varchar(255) not null,
  patronymic varchar(255) not null,
  dateOfBirth date not null,
  sex char(1),
  department_id int not null,
  position_name varchar(255) not null, 
  rank varchar(255) not null,
  primary key (person_id)
) engine=InnoDB;
 
set names 'utf8';
 
insert into department (depName, chief, amount_people) 
values ('відділ програмного забезпечення', 'майор Шмоняк Богдан Миколайович', 13);

 
insert into person (firstName, patronymic, surName, sex, dateOfBirth, department_id, position_name, rank)
values ('Григорій', 'Володимирович', 'Чаплій', 'Ч', '1992-08-11', 1, 'інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС', 'старший лейтенант');
 
