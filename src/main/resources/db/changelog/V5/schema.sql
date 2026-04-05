create table room(
    pet_id INT NOT NULL,
    refrigerator_id INT NULL,
    refrigerator_x INT NULL,
    refrigerator_y INT NULL,
    bookcase_id int NULL,
    bookcase_x INT NULL,
    bookcase_y INT NULL,
    box_newbie1 boolean NOT NULL,
    box_newbie2 boolean NOT NULL,
    box_newbie3 boolean NOT NULL,
    machine_with_drinks_id INT,
    machine_with_drinks_x INT,
    machine_with_drinks_y INT,
    journal_on_floor boolean not null default true,
    every_day_box_last timestamp with time zone,
    every_day_box boolean NOT NULL default false,
    version INT NOT NULL DEFAULT 0,
    PRIMARY KEY(pet_id)
);

alter table room add constraint fk_room_pet_id foreign key (pet_id)
    references pet(id) on update no action on delete no action;

alter table room add constraint fk_room_machine_with_drinks_id foreign key (machine_with_drinks_id)
    references machine_with_drinks(id) on update no action on delete no action;

alter table room add constraint fk_room_bookcase_id
    foreign key (bookcase_id) references bookcase(id)
        on update no action on delete no action;

alter table room add constraint fk_room_refrigerator_id
    foreign key (refrigerator_id) references refrigerator(id)
        ON DELETE NO ACTION ON UPDATE NO ACTION;