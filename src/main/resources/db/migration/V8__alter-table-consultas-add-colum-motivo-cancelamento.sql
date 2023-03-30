alter table consultas add motivoCancelamento tinyInt;
update consultas set motivoCancelamento = null ;