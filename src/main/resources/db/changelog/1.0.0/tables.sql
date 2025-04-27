--liquibase formatted sql

CREATE TABLE user_state (
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    online BOOLEAN NOT NULL,
    last_active TIMESTAMP NOT NULL
);

--rollback drop table if exists user_state;