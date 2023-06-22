/*
 /!\ Please do not modify your code once you have inserted it in the database /!\
 */

/*** t_map_object ***/ -- Clement
CREATE TABLE IF NOT EXISTS t_map_object
(
    obj_id       SERIAL UNIQUE PRIMARY KEY,
    obj_label    VARCHAR(32)  NOT NULL UNIQUE,
    obj_desc     VARCHAR(256) NULL,
    obj_add_date TIMESTAMP DEFAULT NOW(),
    obj_type     VARCHAR(16)  NOT NULL,
    CONSTRAINT C_t_map_object_obj_type CHECK (obj_type IN ('FIELD', 'SENSOR', 'BUILDING', 'PANEL'))
);

/*** t_map_place ***/ -- Clement
CREATE TABLE IF NOT EXISTS t_map_place
(
    place_id    SERIAL PRIMARY KEY,
    place_label VARCHAR(32) NOT NULL,
    place_desc  VARCHAR(256),
    surface     DECIMAL,
    obj_id      INT,
    CONSTRAINT FK_t_map_place_t_map_object FOREIGN KEY (obj_id) REFERENCES t_map_object (obj_id)
);

/*** t_map_position ***/ -- Clement
CREATE TABLE IF NOT EXISTS t_map_position
(
    pos_id      SERIAL,
    obj_id      INT NOT NULL,
    x           INT NOT NULL,
    y           INT NOT NULL,
    point_order INT DEFAULT 0,
    sunshine_lvl INT ,
    CONSTRAINT FK_t_map_position_t_map_object FOREIGN KEY (obj_id) REFERENCES t_map_object (obj_id),
    CONSTRAINT PK_t_map_position PRIMARY KEY (pos_id)
);
/*** t_anemo_value ***/ -- HAMZA
CREATE TABLE IF NOT EXISTS t_anemo_value
(
    value_id        SERIAL PRIMARY key,
    position_anemo  VARCHAR(256) NOT NULL,
    direction_anemo VARCHAR(256) NOT NULL,
    speed_anemo     DECIMAL,
    timeDate_anemo  TIMESTAMP,
    obj_label       VARCHAR(32)  NOT NULL,
    CONSTRAINT FK_t_anemo_value_t_map_object FOREIGN KEY (obj_label) REFERENCES t_map_object (obj_label)
);
/*** t_anemo_config ***/ -- HAMZA
CREATE TABLE IF NOT EXISTS t_anemo_config
(
    id_config       SERIAL PRIMARY KEY,
    level_speed     DECIMAL,
    direction_anemo VARCHAR(256),
    status_anemo    BOOLEAN,
    obj_label       VARCHAR(32) NOT NULL,
    CONSTRAINT FK_t_anemo_config_t_map_object FOREIGN KEY (obj_label) REFERENCES t_map_object (obj_label)
);


/*** t_sol_panel_prop ***/ -- Oussama

Create TABLE IF NOT EXISTS t_sol_panel_prop
(
    id_sol_panel  SERIAL PRIMARY KEY,
    panel_type VARCHAR (32),
    panel_name    VARCHAR(32),
    panel_energy  FLOAT,
    obj_id     INT  NOT NULL,
    CONSTRAINT FK_t_sol_place_t_map_object FOREIGN KEY (obj_id) REFERENCES t_map_object (obj_id)
);



/*** t_off_customer_type***/-- Kebir

Create TABLE IF NOT EXISTS t_off_customer_type
(
    id_customer  SERIAL PRIMARY KEY,
    cust_type VARCHAR (32),
    coefficient int

    );

/*** t_off_product_type***/ --Kebir

Create TABLE IF NOT EXISTS t_off_product_type
(
    id_prod  SERIAL PRIMARY KEY,
    name_product VARCHAR(256),
    poids int,
    obj_id int,
    CONSTRAINT FK_t_off_customer_type_t_map_object FOREIGN KEY (obj_id ) REFERENCES t_map_object( obj_id )


    );
/*** t_off_offer***/-- Kebir

Create TABLE IF NOT EXISTS t_off_offer
(
    id_offer SERIAL PRIMARY KEY,
    reference VARCHAR(256),
    categorie VARCHAR (256),
    date_creation DATE  ,
    date_ligne DATE ,
    date_expiration Date,
    prix int,
    id_customer int,
    id_prod int,

 CONSTRAINT FK_t_off_customer_type_t_off_offer FOREIGN KEY (id_customer) REFERENCES t_off_customer_type(id_customer),
    CONSTRAINT FK_t_off_product_type_t_off_offer FOREIGN KEY (id_prod) REFERENCES t_off_product_type(id_prod)


    );


create or replace procedure add_geo_point(_id INT, _x INT, _y INT)
    language plpgsql as $$
    declare
        _order INT;
        _exist BOOLEAN;
    begin
        select 1 into _exist from t_map_position where obj_id = _id;
        if _exist IS NOT NULL then
            select MAX(point_order) into _order from t_map_position where obj_id = _id;
            insert into t_map_position(obj_id, x, y, point_order) values (_id, _x, _y, _order+1);
        else
            insert into t_map_position(obj_id, x, y, point_order) values (_id, _x, _y, 0);
    end if;
end;$$;

create or replace procedure delete_map_object(_id INT)
    language plpgsql as $$
    declare
        _exist BOOLEAN;
    begin
    select 1 into _exist from t_map_position where obj_id = _id;
    if _exist IS NOT NULL then
        DELETE FROM t_map_position WHERE obj_id = _id;
        DELETE FROM t_map_object WHERE obj_id = _id;
    end if;
end;$$;



