CREATE TABLE pet (
    id serial NOT NULL,
    name varchar(50) NOT NULL,
    session_key varchar(50) DEFAULT NULL,
    created_date timestamp with time zone NOT NULL,
    login_date timestamp with time zone DEFAULT NULL,
    satiety INT NOT NULL DEFAULT 0,
    mood INT NOT NULL DEFAULT 0,
    education INT NOT NULL DEFAULT 0,
    drink INT NOT NULL DEFAULT 0,
    comment varchar(50) DEFAULT NULL,
    user_id INT NOT NULL,
    pet_type INT NOT NULL DEFAULT 0,
    hat_id varchar(50) DEFAULT NULL,
    cloth_id varchar(50) DEFAULT NULL,
    bow_id varchar(50) DEFAULT NULL,
    experience INT NOT NULL DEFAULT 0,
    level_id INT NOT NULL DEFAULT 1,
    eat_count INT NOT NULL default 0,
    drink_count INT NOT NULL default 0,
    teach_count INT NOT NULL default 0,
    build_count INT NOT NULL default 0,
    hidden_objects_game_count INT default 0,
    every_day_login_last timestamp with time zone NULL,
    every_day_login_count INT NOT NULL DEFAULT 0,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

create unique index idx_pet_session_key on pet(session_key);

create index idx_pet_user_id on pet(user_id);

create table level(
    id INT NOT NULL,
    experience INT NOT NULL,
    PRIMARY KEY(id)
);

insert into level(id, experience) values(1, 0);
insert into level(id, experience) values(2, 10);
insert into level(id, experience) values(3, 30);
insert into level(id, experience) values(4, 60);
insert into level(id, experience) values(5, 180);

alter table pet add constraint fk_pet_level_id foreign key (level_id)
    references level(id) on update no action on delete no action;