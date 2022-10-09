insert into offer (`value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (50, '2022-2-1', 'eid al-fitr', 'FESTIVAL', now(), 'DBA');
insert into offer (`value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (39, '2022-4-12', 'Arash kamangir''s eid', 'AZADI', now(), 'DBA');
insert into offer (`value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (66, '2022-6-24', 'eid al-qadir', 'FESTIVAL', now(), 'DBA');
insert into offer (`value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (49, '2022-11-22', '22 bahman''s holiday', 'AZADI', now(), 'DBA');
insert into offer (`value`, `date`, `reason`, `type`, `created_at`, `created_by`)
values (35, '2023-1-1', 'eid noroz', 'FESTIVAL', now(), 'DBA');

insert into role (name, created_at, created_by)
values ('ADMIN', curdate(), 'DBA');
insert into role(name, created_at, created_by)
values ('CUSTOMER', curdate(), 'DBA');
insert into User (name, email, mobile_num, password, role_id, created_at, created_by)
values ('admin', 'mramz55555@gmail.com', '09199658784', '$2a$10$Ct7Pwoo5zirXT8uYqiaIDe2fmQAHyOvteBDQWMa2NWxcEdCQ9Ek1e',
        1, curdate(), 'DBA');
