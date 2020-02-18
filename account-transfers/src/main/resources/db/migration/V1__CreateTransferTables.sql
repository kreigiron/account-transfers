create table account (
    id long not null PRIMARY KEY,
    name varchar(256) not null,
    number varchar(20) not null,
    balance decimal(10,2) not null
);

create table transfer (
    id long not null PRIMARY KEY,
    origin_account varchar(32) not null,
    destination_account varchar(32) not null,
    amount decimal(10,2) not null,
    currency varchar(3) not null,
    status varchar(16) not null,
    status_reason varchar(256) null
);

