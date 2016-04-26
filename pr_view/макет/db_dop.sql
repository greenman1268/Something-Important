USE staff;
drop tables person;
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

insert into person (firstName, patronymic, surName, sex, birthDay, department_id, position_name, rank)
values ('Григорій', 'Володимирович', 'Чаплій', 'Ч', '1992-08-11', 1, 'інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС', 'старший лейтенант');
 
insert into person (firstName, patronymic, surName, sex, birthDay, department_id, position_name, rank)
values ('Віталій', 'Валерійович', 'Гуцол', 'Ч', '1991-11-20', 1, 'інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС', 'старший лейтенант');