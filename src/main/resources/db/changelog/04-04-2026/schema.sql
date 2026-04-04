create table refrigerator(
    id INT NOT NULL,
    experience INT NOT NULL,
    PRIMARY KEY (id)
);

insert into refrigerator(id, experience) values(1, 1);
insert into refrigerator(id, experience) values(2, 3);
insert into refrigerator(id, experience) values(3, 8);
insert into refrigerator(id, experience) values(4, 11);
insert into refrigerator(id, experience) values(5, 14);
insert into refrigerator(id, experience) values(6, 17);

create table food(
    id varchar(50) NOT NULL,
    refrigerator_id INT NOT NULL,
    refrigerator_order INT NOT NULL,
    hidden_objects_game_drop_rate REAL NOT NULL,
    PRIMARY KEY(id)
);
alter table food add constraint fk_food_refrigerator_id foreign key (refrigerator_id)
    references refrigerator(id) on update no action on delete no action;