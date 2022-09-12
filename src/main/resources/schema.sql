CREATE TABLE IF NOT EXISTS hardware(
    h_id identity,
    h_code varchar(10) not null unique,
    h_name varchar(255) not null,
    h_price double not null,
    h_type varchar(100) not null,
    h_num_available integer not null
);

CREATE TABLE IF NOT EXISTS review(
    r_id identity,
    h_id integer not null,
    r_title varchar(100) not null,
    r_text varchar(510) not null,
    r_grade varchar(10) not null,
    FOREIGN KEY (h_id) REFERENCES hardware(h_id)
);

CREATE TABLE IF NOT EXISTS users(
    id identity,
    username varchar(255) not null unique,
    password varchar(255) not null
);

CREATE TABLE IF NOT EXISTS authority(
    id identity,
    authority_name varchar(255) not null unique
);

CREATE TABLE IF NOT EXISTS users_authority(
    id identity,
    users_id bigint not null,
    authority_id bigint not null,
    CONSTRAINT fk_user FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authority(id)
);

