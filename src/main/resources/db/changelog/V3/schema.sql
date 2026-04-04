create table building_material(
    id varchar(50) NOT NULL,
    rucksack_order INT NOT NULL,
    newbie_box_drop_min INT NOT NULL,
    newbie_box_drop_max INT NOT NULL,
    newbie_box_drop_rate REAL NOT NULL,
    hidden_objects_game_drop_rate REAL NOT NULL,
    PRIMARY KEY (id)
);

insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('TIMBER', 0, 1, 3, 0.6, 0.3);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('BOARD', 1, 1, 3, 0.3, 0.3);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('STONE', 2, 0, 0, 0.0, 0.3);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('CHIP', 3, 0, 0, 0.0, 0.1);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('WIRE', 4, 0, 0, 0.0, 0.1);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('IRON', 5, 0, 0, 0.0, 0.1);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('OIL', 6, 0, 0, 0.0, 0.1);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('BLUE_CRYSTAL', 7, 0, 0, 0.0, 0.1);
insert into building_material(id, rucksack_order, newbie_box_drop_min, newbie_box_drop_max, newbie_box_drop_rate, hidden_objects_game_drop_rate)
    values('RUBBER', 8, 0, 0, 0.0, 0.1);

create table refrigerator_cost(
    id serial NOT NULL,
    refrigerator_id INT NOT NULL,
    building_material_id varchar(50) NOT NULL,
    cost INT NOT NULL,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

alter table refrigerator_cost add constraint fk_refrigerator_cost_refrigerator_id
    foreign key (refrigerator_id) references refrigerator(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table refrigerator_cost add constraint fk_refrigerator_cost_building_material_id
    foreign key (building_material_id) references building_material(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(1, 'TIMBER', 2);

create unique index idx_refrigerator_cost_unique on refrigerator_cost(refrigerator_id, building_material_id);

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(2, 'TIMBER', 5);

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(2, 'STONE', 5);

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(1, 'STONE', 2);

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(3, 'IRON', 3); -- 3 железа

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(3, 'CHIP', 3); -- 3 микросхемы

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(4, 'IRON', 4); -- 4 железа

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(4, 'CHIP', 4); -- 4 микросхемы

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(5, 'IRON', 8); -- 8 железа

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(5, 'CHIP', 5); -- 5 микросхемы

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(6, 'IRON', 10); -- 10 железа

insert into refrigerator_cost(refrigerator_id, building_material_id, cost)
    values(6, 'CHIP', 6); -- 6 микросхемы

