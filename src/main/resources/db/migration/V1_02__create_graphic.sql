-- create table graphic

create table graphic
(
    id            bigint not null primary key,
    type          text,
    size          int,
    rotation      int,
    fill          text,
    fillopacity   text,
    stroke        text,
    strokeopacity int,
    strokewidth   int,
    name          text
);

create unique index graphic_name_ix on graphic (name);

ALTER TABLE graphic
ALTER
COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
        SEQUENCE NAME graphic_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );
