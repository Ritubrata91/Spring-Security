create table users(
    USERNAME varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table authorities (
    USERNAME varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(USERNAME) references users(USERNAME)
);
create unique index ix_auth_username on authorities (USERNAME,authority);