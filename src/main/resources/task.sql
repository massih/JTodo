DROP TABLE IF EXISTS jtask;
CREATE TABLE jtask
(
    id            BIGINT default not null
                  constraint jtask_pk
                  primary key,
    description   text not null,
    repeat_type   text not null,
    repeat_number integer
);


DROP TABLE IF EXISTS task_log;
CREATE TABLE task_log
(
    id          uuid not null
                constraint task_log_pk
                primary key,
    jtask_id    uuid
                constraint task_log_jtask_null_fk
                references jtask (id),
    date        date not null
);


