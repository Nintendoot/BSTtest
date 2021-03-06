drop table if exists departments CASCADE;
drop table if exists worked_hours CASCADE;
drop table if exists workers CASCADE;
drop table if exists warkers_department CASCADE;

create table warkers_department
(
    departments_id bigint,
    worker_id      bigint not null,
    primary key (worker_id)
);

create table departments
(
    departments_id bigint generated by default as identity,
    name           varchar(255),
    primary key (departments_id)
);

create table workers
(
    worker_id bigint generated by default as identity,
    last_name varchar(255),
    name      varchar(255),
    position  varchar(255),
    primary key (worker_id)
);

create table worked_hours
(
    worked_hours_id bigint generated by default as identity,
    end_work        timestamp,
    start_work      timestamp,
    worker_id       bigint,
    primary key (worked_hours_id)

);

insert into departments(departments_id, name)
values (null, 'Develop'),
       (null, 'Test');

insert into warkers_department(departments_id, worker_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 5);



insert into worked_hours(worked_hours_id, end_work, start_work, worker_id)
values (null, '2021-07-02T19:17:12.936', '2021-07-02T19:17:12.936', 1),
       (null, '2021-07-02T19:17:12.936', '2021-07-02T19:17:12.936', 2),
       (null, '2021-07-02T19:17:12.33', '2021-07-02T19:17:12.936', 1),
       (null, '2021-07-02T19:17:12.33', '2021-07-02T19:17:12.936', 3),
       (null, '2021-07-02T19:17:12.33', '2021-07-02T19:17:12.936', 1),
       (null, '2021-07-02T19:17:12.33', '2021-07-02T19:17:12.936', 4),
       (null, '2021-07-02T19:17:12.33', '2021-07-02T19:17:12.936', 5);



insert into workers(worker_id, name, last_name, position)
values (null, 'Oleg', 'Tereshkov', 'Programmer'),
       (null, 'Misha', 'Zabotin', 'Programmer'),
       (null, 'Grisha', 'Zavedenko', 'Java Programmer'),
       (null, 'Olga', 'Mishystina', 'Java Programmer'),
       (null, 'Andrey', 'Solovey', 'Test');



alter table warkers_department
    add constraint FK9l8jouusekypd07sepy19uqcl foreign key (departments_id) references departments;
alter table warkers_department
    add constraint FKq5759qwm37s9frs44st7w4nk6 foreign key (worker_id) references workers;
alter table worked_hours
    add constraint FKbnra0tx979gf3djno842cigu foreign key (worker_id) references workers;
