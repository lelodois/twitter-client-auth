
create table if not exists AUTHUSER (
  username          varchar(50) not null primary key,
  email             varchar(50),
  password          varchar(500),
  activated         boolean default false,
  activationkey     varchar(50) default null,
  resetpasswordkey  varchar(50) default null
);

create table if not exists AUTHORITY (
  name varchar(50) not null primary key
);

create table if not exists USER_AUTHORITY (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references AUTHUSER (username),
    foreign key (authority) references AUTHORITY (name),
    unique index user_authority_idx_1 (username, authority)
);