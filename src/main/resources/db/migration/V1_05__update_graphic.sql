-- update table graphic
alter table graphic
add
    welknownname text;

create index graphic_welknownname_ix on graphic (welknownname);


alter table symbol
    add
        welknownname text;

create index symbol_welknownname_ix on graphic (welknownname);
