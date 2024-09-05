-- create table norm

create table norm
(
    id                   bigint not null primary key,

    type                 text,
    name                 text,
    fill                 text,
    fillopacity          float,
    stroke               text,
    strokeopacity        int,
    strokewidth          float,
    strokelinejoinfill   text,
    strokelinejoinstroke text,
    strokedasharray      text,
    symbol               text
);

create unique index norm_name_ix on norm (name);

ALTER TABLE norm
ALTER
COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
        SEQUENCE NAME norm_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );
