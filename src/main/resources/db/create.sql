
create table passports
(
    id        serial primary key,
    series    int          not null,
    number    int          not null,
    expired   timestamp,
    surname   varchar(500) not null,
    firstname varchar(500) not null,
    address   varchar(2000)
);