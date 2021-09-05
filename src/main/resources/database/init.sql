drop table if exists nest cascade ;

drop table if exists bird cascade ;

CREATE TABLE IF NOT EXISTS nest
(
    id bigint NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT nest_pkey  PRIMARY KEY (id)
);
drop sequence if exists nest_id_seq;
CREATE SEQUENCE if not exists nest_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS bird
(
    id bigint NOT NULL,
    color character varying(255) COLLATE pg_catalog."default",
    is_flying_bird boolean NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    id_nest bigint,
    CONSTRAINT bird_pkey PRIMARY KEY (id),
    CONSTRAINT fkjotn9fs8je7lcvbyq927y1ve4 FOREIGN KEY (id_nest)
        REFERENCES nest (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
drop sequence if exists bird_id_seq;
CREATE SEQUENCE if not exists bird_id_seq minvalue 1 START WITH 1 INCREMENT BY 1;