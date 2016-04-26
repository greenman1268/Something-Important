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
  firstName varchar(255),
  surName varchar(255),
  patronymic varchar(255),
  birthDay datetime,
  sex char(1),
  department_id int,
  position_name varchar(255), 
  rank varchar(255),
  primary key (person_id)
) engine=InnoDB;
 
set names 'utf8';
 
insert into department (depName, chief, amount_people) 
values ('відділ програмного забезпечення', 'майор Шмоняк Богдан Миколайович', 13);

insert into department (depName, chief, amount_people) 
values ('відділ блабла', 'майор Шестаков Дмитро Дмитрович', 10);
 
insert into person (firstName, patronymic, surName, sex, birthDay, department_id, position_name, rank)
values ('Григорій', 'Володимирович', 'Чаплій', 'Ч', '1992-08-11', 1, 'інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС', 'старший лейтенант');
 
insert into person (firstName, patronymic, surName, sex, birthDay, department_id, position_name, rank)
values ('Віталій', 'Валерійович', 'Гуцол', 'Ч', '1991-11-20', 1, 'інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС', 'старший лейтенант');