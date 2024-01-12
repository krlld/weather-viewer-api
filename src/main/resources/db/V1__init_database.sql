CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS locations
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    user_id   BIGINT  NOT NULL,
    latitude  DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS sessions
(
    id         VARCHAR PRIMARY KEY,
    user_id    BIGINT    NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);