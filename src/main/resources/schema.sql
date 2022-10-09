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
    add constraint CLASS_ID_FK foreign key(business_id) references class(id);