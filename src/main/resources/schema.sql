use
`isoft-db`;
CREATE TABLE contact_message
(
    `id`         int auto_increment PRIMARY KEY,
    `name`       varchar(100) NOT NULL,
    `mobile_num` varchar(11)  NOT NULL,
    `email`      varchar(100) NOT NULL,
    `subject`    varchar(100) NOT NULL,
    `message`    varchar(500) NOT NULL,
    `status`     varchar(10)  NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);


CREATE TABLE offer
(
    `id`         int auto_increment,
    `value`      int,
    `date`       varchar(20)  NOT NULL,
    `reason`     varchar(100) NOT NULL,
    `type`       varchar(20)  NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    primary key (id)
);

create table role
(
    id         int         not null auto_increment,
    name       varchar(50) not null,
    created_at timestamp   not null,
    created_by varchar(50) not null,
    updated_at timestamp   default null,
    update_by  varchar(50) default null,
    primary key (id)
);

create table address
(
    id          int auto_increment not null,
    address_one varchar(200) not null,
    address_two varchar(200) not null,
    city        varchar(50)  not null,
    zip_code    int          not null,
    created_at  timestamp    not null,
    created_by  varchar(50)  not null,
    updated_at  timestamp   default null,
    update_by   varchar(50) default null,
    primary key (id)
);

create table user
(
    id            int auto_increment not null,
    name          varchar(50) not null,
    email         varchar(50) not null,
    mobile_number varchar(11) not null,
    password      varchar(50) not null,
    role_id       int         not null,
    address_id    int null,
    created_at    timestamp   not null,
    created_by    varchar(50) not null,
    updated_at    timestamp   default null,
    update_by     varchar(50) default null,
    primary key (id),
    foreign key (role_id) references role (id),
    foreign key (address_id) references address (id)
);


create table class
(
    id         int auto_increment not null,
    name       varchar(100) not null,
    created_at timestamp    not null,
    created_by varchar(50)  not null,
    updated_at timestamp    not null,
    updated_by varchar(50)  not null,
    primary key (id)
);

alter table user
    add column class_id int null after address_id,
    add constraint CLASS_ID_FK foreign key(app_id) references class(id);


create table app
(
    id         int          not null auto_increment,
    name       varchar(100) not null,
    cost       varchar(10)  not null,
    created_at timestamp    not null,
    created_by varchar(50)  not null,
    updated_at timestamp    not null,
    updated_by varchar(50)  not null,
    primary key (id)
);

create table app_user
(
    app_id  int not null,
    user_id int not null,
    foreign key (app_id) references app (id),
    foreign key (user_id) references user (id),
    primary key (app_id, user_id)
);



#
use `isoft-db`;
#
#
CREATE TABLE student
(
    id   int PRIMARY KEY,
    name varchar(30) default ''
);
#
# insert into student (id,name) values(1,'ali');
#
insert into student (id,name) values(2,'mohammad');
#
insert into student (id,name) values(3,'zamani');
#
insert into student (id,name) values(4,'hassan');
#
insert into student (id,name) values(5,'hossein');
#
insert into student (id,name) values(6,'ahmad');
#
#
#
CREATE TABLE course
(
    id         int PRIMARY KEY,
    name       varchar(30) default '',
    student_id int,
    foreign KEY (student_id) references student (id)
);
#
#
alter table student
    # add column course_id int null after name,
    # add constraint COURSE_ID_FK foreign key (course_id) references course(id);
#
# insert into course (id,name,student_id) values (1,'a',1);
#
insert into course (id,name,student_id) values (2,'b',1);
#
insert into course (id,name,student_id) values (3,'c',2);
#
insert into course (id,name,student_id) values (4,'d',3);
#
insert into course (id,name,student_id) values (5,'e',4);
#
insert into course (id,name,student_id) values (6,'f',4);
#
insert into course (id,name,student_id) values (7,'a',5);
#
insert into course (id,name,student_id) values (8,'a',5);
#
insert into course (id,name,student_id) values (9,'a',6);
#
insert into course (id,name,student_id) values (10,'a',6);
#
insert into course (id,name,student_id) values (11,'a',6);
#
#
select *
from student # #
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;
#
update student
set course_id=1
where id = 1;









