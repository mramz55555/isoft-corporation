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