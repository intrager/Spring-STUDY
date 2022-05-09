create table simple_bbs(
    id number(4) primary key,
    writer varchar2(100),
    title varchar2(100),
    content varchar2(100)
);

create sequence simple_bbs_seq;