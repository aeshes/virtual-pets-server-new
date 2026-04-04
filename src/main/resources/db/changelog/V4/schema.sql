CREATE TABLE cloth (
    id varchar(50) NOT NULL,
    cloth_type varchar(50) NOT NULL,
    wardrobe_order INT NOT NULL,
    hidden_objects_game_drop_rate REAL NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE cloth ADD CONSTRAINT chk_cloth_type CHECK (cloth_type IN ('HAT', 'CLOTH', 'BOW'));

INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('RED_HAT', 'HAT', 0, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('COWBOY_HAT', 'HAT', 1, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('TIARA', 'HAT', 2, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('COLORED_BODY', 'CLOTH', 0, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('SUIT_JACKET', 'CLOTH', 1, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('PINKY_WINGS', 'CLOTH', 2, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('RED_BOW', 'BOW', 0, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('BLUE_BOW', 'BOW', 1, 0.1);
INSERT INTO cloth(id, cloth_type, wardrobe_order, hidden_objects_game_drop_rate)
    VALUES('BLUE_FLOWER', 'BOW', 2, 0.1);

create table pet_food(
    id serial NOT NULL,
    pet_id INT NOT NULL,
    food_id varchar(50) NOT NULL,
    food_count INT NOT NULL,
    version INT NOT NULL DEFAULT 0 ,
    PRIMARY KEY(id)
);

create unique index idx_pet_food_unique on pet_food(pet_id, food_id);

alter table pet_food add constraint fk_pet_food_pet_id
    foreign key (pet_id) references pet(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table pet_food add constraint fk_pet_food_food_id
    foreign key (food_id) references food(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

create table pet_building_material(
    id serial NOT NULL,
    pet_id INT NOT NULL,
    building_material_id varchar(50) NOT NULL,
    building_material_count INT NOT NULL,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

create unique index idx_pet_building_material_unique on pet_building_material(pet_id, building_material_id);

create index idx_pet_building_materials_pet_id on pet_building_material(pet_id);

alter table pet_building_material add constraint fk_pet_building_material_pet_id
    foreign key (pet_id) references pet(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table pet_building_material add constraint fk_pet_building_material_building_material_id
    foreign key (building_material_id) references building_material(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

create table bookcase(
    id INT NOT NULL,
    experience INT NOT NULL,
    PRIMARY KEY(id)
);

insert into bookcase(id, experience) values(1, 1);
insert into bookcase(id, experience) values(2, 3);
insert into bookcase(id, experience) values(3, 7);
insert into bookcase(id, experience) values(4, 13);
insert into bookcase(id, experience) values(5, 17);
insert into bookcase(id, experience) values(6, 21);

create table bookcase_cost(
    id serial NOT NULL,
    bookcase_id INT NOT NULL,
    building_material_id varchar(50) NOT NULL,
    cost INT NOT NULL,
    PRIMARY KEY (id)
);

alter table bookcase_cost add constraint fk_bookcase_cost_bookcase_id
    foreign key (bookcase_id) references bookcase(id)
        on update no action on delete no action;

alter table bookcase_cost add constraint fk_bookcase_cost_building_material_id
    foreign key (building_material_id) references building_material(id)
        on update no action on delete no action;

create unique index idx_bookcase_cost_unique on bookcase_cost(bookcase_id, building_material_id);

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(1, 'TIMBER', 1);

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(2, 'TIMBER', 2); -- 2 дерева

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(3, 'BOARD', 3); -- 3 доски

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(4, 'BOARD', 4);  -- 4 доски

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(5, 'TIMBER', 5); -- 5 дерева

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(5, 'BOARD', 5); -- 5 досок

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(6, 'TIMBER', 6);  -- 6 дерева

insert into bookcase_cost(bookcase_id, building_material_id, cost)
    values(6, 'BOARD', 6);  -- 6 досок


create table book(
    id varchar(50) NOT NULL,
    bookcase_id INT NOT NULL,
    bookcase_order INT NOT NULL,
    hidden_objects_game_drop_rate real NOT NULL,
    PRIMARY KEY(id)
);

alter table book add constraint fk_book_bookcase_id foreign key (bookcase_id)
    references bookcase(id) on update no action on delete no action;

CREATE TABLE pet_book (
    id serial NOT NULL,
    pet_id INT NOT NULL,
    book_id varchar(50) NOT NULL,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

create unique index idx_pet_book_pet_id_book_id on pet_book(pet_id, book_id);

alter table pet_book add constraint fk_pet_book_pet_id foreign key (pet_id)
    references pet(id) on update no action on delete no action;

alter table pet_book add constraint fk_pet_book_book_id foreign key (book_id)
    references book(id) on update no action on delete no action;

create table drink(
    id varchar(50) NOT NULL,
    machine_with_drinks_id INT NOT NULL,
    machine_with_drinks_order INT NOT NULL,
    hidden_objects_game_drop_rate REAL NOT NULL,
    PRIMARY KEY(id)
);

create table machine_with_drinks(
    id INT NOT NULL,
    experience INT NOT NULL,
    PRIMARY KEY(id)
);

create table machine_with_drinks_cost(
    id serial NOT NULL,
    machine_with_drinks_id INT NOT NULL,
    building_material_id varchar(50) NOT NULL,
    cost INT NOT NULL,
    version INT NOT NULL default 0,
    PRIMARY KEY(id)
);

alter table machine_with_drinks_cost add constraint fk_machine_with_drinks_cost_machine_with_drinks_id
    foreign key(machine_with_drinks_id) references machine_with_drinks(id)
        on update no action on delete no action;

alter table machine_with_drinks_cost add constraint fk_machine_with_drinks_cost_building_material_id
    foreign key(building_material_id) references building_material(id)
        on update no action on delete no action;

insert into machine_with_drinks(id, experience) values(1, 1);
insert into machine_with_drinks(id, experience) values(2, 3);
insert into machine_with_drinks(id, experience) values(3, 7);
insert into machine_with_drinks(id, experience) values(4, 11);
insert into machine_with_drinks(id, experience) values(5, 12);
insert into machine_with_drinks(id, experience) values(6, 16);



insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values(1, 'TIMBER', 1);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values(1, 'STONE', 1);

insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (2, 'BOARD', 2);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (2, 'WIRE', 2);

insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (3, 'BOARD', 3);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (3, 'CHIP', 1);

insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (4, 'BOARD', 4);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (4, 'STONE', 1);

insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (5, 'BOARD', 5);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (5, 'STONE', 5);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (5, 'CHIP', 1);

insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (6, 'BOARD', 6);
insert into machine_with_drinks_cost(machine_with_drinks_id, building_material_id, cost) values (6, 'STONE', 6);

create table pet_journal_entry(
    id serial NOT NULL,
    created_at timestamp with time zone NOT NULL,
    pet_id INT NOT NULL,
    journal_entry_id varchar(50) NOT NULL,
    readed boolean NOT NULL default false,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

alter table pet_journal_entry add constraint fk_pet_journal_entry_pet_id foreign key (pet_id)
    references pet(id) on update no action on delete no action;

create table pet_achievement(
    id serial NOT NULL,
    pet_id INT NOT NULL,
    achievement_id varchar(50) NOT NULL,
    was_shown boolean default false,
    version INT NOT NULL default 0,
    PRIMARY KEY(id)
);

alter table pet_achievement add constraint fk_pet_achievement_pet_id foreign key (pet_id)
    references pet(id) on update no action on delete no action;

insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('CARROT',         1, 0, 0.5);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('DRY_FOOD',       1, 1, 0.5);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('FISH',           1, 2, 0.5);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('ICE_CREAM',      2, 0, 0.4);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('APPLE',          2, 1, 0.4);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('CABBAGE',        2, 2, 0.4);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('CHOCOLATE',      3, 0, 0.3);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('FRENCH_FRIES',   3, 1, 0.3);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('JAPANESE_ROLLS', 3, 2, 0.3);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('PIE',            4, 0, 0.2);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('POTATOES',       4, 1, 0.2);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('SANDWICH',       4, 2, 0.2);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('BANANA',         5, 0, 0.1);
insert into food(id, refrigerator_id, refrigerator_order, hidden_objects_game_drop_rate)
    values('WATERMELON',     4, 1, 0.1);





insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('WATER',        1, 0, 1.0);
insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('MILK',         2, 0, 0.5);
insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('BOTTLE',       3, 0, 0.4);
insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('TEA',          4, 0, 0.2);
insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('COFFEE',       5, 0, 0.2);
insert into drink(id, machine_with_drinks_id, machine_with_drinks_order, hidden_objects_game_drop_rate)
    values('ORANGE_JUICE', 6, 0, 0.1);




insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('DESTINY',       1, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('SQL',           1, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('PURPLE',        1, 2, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('PLAID',         2, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('PUSHKIN',       2, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('BLACK',         2, 2, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('WHITE',         3, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('DIRTY',         3, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('EARTH',         3, 2, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('MOON_AND_STAR', 4, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('GIRL',          4, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('SUNSET',        4, 2, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('SAGA',          5, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('NONAME',        5, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('CATS',          5, 2, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('GOLD_TITLE',    6, 0, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('DARK',          6, 1, 0.1);
insert into book (id, bookcase_id, bookcase_order, hidden_objects_game_drop_rate)
    values('SCHEME',        6, 2, 0.1);

insert into pet_book(pet_id, book_id)
    select p.id, 'SQL'
    from pet p
    where not exists(select * from pet_book pb where pb.pet_id = p.id and pb.book_id = 'SQL');