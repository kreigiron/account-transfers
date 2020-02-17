create table account (
    id long not null,
    name varchar(256) not null,
    number varchar(20) not null
);

create table transfer (
    id long not null,
    origin_account long not null,
    destination_account long not null,
    amount decimal(10,2) not null,
    status int not null,
    status_reason varchar(256) not null
);

