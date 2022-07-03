create table user_list (
    name varchar2(20) primary key,
    password varchar2(100),
    authority varchar(20),
    enabled number(1)
);

insert into user_list values ('user', '암호화된 패스워드', 'ROLE_USER', 1);
insert into user_list values ('admin', '암호화된 패스워드', 'ROLE_ADMIN', 1);
commit;

drop table user_list;

update user_list set password = '$2a$10$hK2hsCp.4GOtqRvcS.ALC.0K1vZVHyD8lsXPQ2UfEFcRYstsX1QOq';
commit;

select * from user_list;