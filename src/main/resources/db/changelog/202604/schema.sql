CREATE TABLE "user" (
    id serial NOT NULL,
    login varchar(50) DEFAULT NULL,
    name varchar(50) NOT NULL,
    password varchar(100) NULL,
    enabled boolean NOT NULL default true,
    registration_date timestamp with time zone NOT NULL,
    login_date timestamp with time zone DEFAULT NULL,
    active_date timestamp with time zone DEFAULT NULL,
    sex varchar(5) DEFAULT NULL,
    birthdate date DEFAULT NULL,
    comment varchar(50) DEFAULT NULL,
    country varchar(50) DEFAULT NULL,
    city varchar(50) DEFAULT NULL,
    roles varchar(50) NOT NULL DEFAULT 'USER',
    email varchar(100) DEFAULT NULL,
    recover_password_key varchar(50) DEFAULT NULL,
    recover_password_valid timestamp with time zone DEFAULT NULL,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

create unique index idx_user_login on "user"(login);

ALTER TABLE "user" ADD CONSTRAINT chk_user_sex CHECK(sex = 'MAN' OR sex = 'WOMAN');

INSERT INTO "user"(login, name, password, registration_date, roles)
values('admin', 'Admin','{bcrypt}$2a$10$JT0l8oNHQuohL8SMLHCBludsjTiJNpG.uDHc3QGkP5V.aMMLSEa7G',now(),'ADMIN,USER');

INSERT INTO "user"(login, name, password, registration_date, roles)
values('test', 'Tester','{bcrypt}$2a$10$JT0l8oNHQuohL8SMLHCBludsjTiJNpG.uDHc3QGkP5V.aMMLSEa7G',now(),'USER');