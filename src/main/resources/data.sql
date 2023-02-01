insert into offer (id, `value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (1, 50, '2022-2-1', 'eid al-fitr', 'FESTIVAL', now(), 'DBA')
     , (2, 39, '2022-4-12', 'Arash kamangir''s eid', 'AZADI', now(), 'DBA')
     , (3, 66, '2022-6-24', 'eid al-qadir', 'FESTIVAL', now(), 'DBA')
     , (4, 49, '2022-11-22', '22 bahman''s holiday', 'AZADI', now(), 'DBA')
     , (5, 35, '2023-1-1', 'eid noroz', 'FESTIVAL', now(), 'DBA');

insert into role (id, name, created_at, created_by)
values (1, 'ROLE_ADMIN', curdate(), 'DBA')
     , (2, 'ROLE_CUSTOMER', curdate(), 'DBA');
insert into user (id, name, email, mobile_num, password, role_id, created_at, created_by)
values (3, 'admin', 'mramz55555@gmail.com', '09199658784',
        '$2a$10$Ct7Pwoo5zirXT8uYqiaIDe2fmQAHyOvteBDQWMa2NWxcEdCQ9Ek1e',
        1, curdate(), 'DBA');


insert into student (id, name)
values (1, 'ali');
insert into student (id, name)
values (2, 'mohammad');
insert into student (id, name)
values (3, 'zamani');
insert into student (id, name)
values (4, 'hassan');
insert into student (id, name)
values (5, 'hossein');
insert into student (id, name)
values (6, 'ahmad');

insert into course (id, name, student_id)
values (1, 'a', 1);

insert into course (id, name, student_id)
values (2, 'b', 1);

insert into course (id, name, student_id)
values (3, 'c', 2);

insert into course (id, name, student_id)
values (4, 'd', 3);

insert into course (id, name, student_id)
values (5, 'e', 4);

insert into course (id, name, student_id)
values (6, 'f', 4);

insert into course (id, name, student_id)
values (7, 'a', 5);

insert into course (id, name, student_id)
values (8, 'a', 5);

insert into course (id, name, student_id)
values (9, 'a', 6);

insert into course (id, name, student_id)
values (10, 'a', 6);

insert into course (id, name, student_id)
values (11, 'a', 6);
