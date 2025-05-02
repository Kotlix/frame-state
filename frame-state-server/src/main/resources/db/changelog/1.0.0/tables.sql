--liquibase formatted sql

--changeset AMGureev:1.0.0:1
CREATE TABLE user_state (
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    online BOOLEAN NOT NULL,
    last_active timestamptz NOT NULL
);

--rollback drop table if exists user_state;