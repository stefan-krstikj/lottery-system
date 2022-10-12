create table if not exists users
(
    id                         bigserial primary key,
    username                   text not null,
    password                   text not null,
    first_name                 text not null,
    last_name                  text not null,
    is_account_non_expired     bool not null,
    is_account_non_locked      bool not null,
    is_credentials_non_expired bool not null,
    is_enabled                 bool not null
);

create table if not exists lottery
(
    id             bigserial primary key not null,
    date           date                  not null,
    lottery_status text                  not null
);

create table if not exists ballot
(
    id             bigserial primary key not null,
    uuid           uuid                  not null,
    lottery_id     bigint references lottery (id),
    participant_id bigint references users (id)
);

alter table lottery
    add column
        winning_ballot_id bigint references ballot (id) default null;
