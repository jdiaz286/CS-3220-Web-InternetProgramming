drop table if exists hw3_groups;
drop table if exists hw3_students;

create table hw3_groups (
    id          integer auto_increment primary key,
    name        varchar(255) unique not null,
    max_size    integer default 5
);

create table hw3_students (
    id              integer auto_increment primary key,
    name            varchar(255) not null,
    birth_year      integer,
    parent_name     varchar(255),
    parent_email    varchar(255),
    group_id        integer references student_groups(id),
    group_name 		varchar(255)
);

insert into hw3_groups (name) values ('group 1');
insert into hw3_groups (name) values ('group 2');

insert into hw3_students (name, birth_year, parent_name, parent_email, group_id, group_name) values('Jonathan', 20, 'parent name', 'parent email', 1, 'group 1');
insert into hw3_students (name, birth_year, parent_name, parent_email, group_id, group_name) values('Diana', 20, 'parent name', 'parent email', 1, 'group 1');
insert into hw3_students (name, birth_year, parent_name, parent_email, group_id, group_name) values('Kelly', 20, 'parent name', 'parent email', 1, 'group 1');
insert into hw3_students (name, birth_year, parent_name, parent_email, group_id, group_name) values('Beatriz', 20, 'parent name', 'parent email', 1, 'group 1');
insert into hw3_students (name, birth_year, parent_name, parent_email, group_id, group_name) values('Ricardo', 20, 'parent name', 'parent email', 2, 'group 2');

select * from hw3_students;
select * from hw3_groups;