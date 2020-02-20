create table account (
    id long IDENTITY not null PRIMARY KEY ,
    name varchar(256) not null,
    number varchar(20) not null,
    balance decimal(10,2) not null
);

create table transfer (
    id long IDENTITY not null PRIMARY KEY,
    originaccount_id varchar(32) not null,
    destinationaccount_id varchar(32) not null,
    amount decimal(10,2) not null,
    status varchar(16) not null,
    statusreason varchar(256) null
);

create index accountnumberidx on account(number);
