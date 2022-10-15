CREATE TABLE IF NOT EXISTS users
(
    id                         BIGSERIAL PRIMARY KEY,
    username                   text NOT NULL,
    password                   text NOT NULL,
    first_name                 text NOT NULL,
    last_name                  text NOT NULL,
    is_account_non_expired     bool NOT NULL,
    is_account_non_locked      bool NOT NULL,
    is_credentials_non_expired bool NOT NULL,
    is_enabled                 bool NOT NULL
);


CREATE TABLE IF NOT EXISTS lottery
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    date           date                  NOT NULL,
    lottery_status text                  NOT NULL
);


CREATE TABLE IF NOT EXISTS ballot
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    UUID           UUID                  NOT NULL,
    lottery_id     bigint REFERENCES lottery (id),
    participant_id bigint REFERENCES users (id)
);


ALTER TABLE lottery
    ADD COLUMN winning_ballot_id bigint REFERENCES ballot (id) DEFAULT NULL;
