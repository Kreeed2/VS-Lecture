# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table message (
  id                            integer not null,
  version                       integer,
  author                        varchar(255),
  message                       varchar(255),
  creation                      timestamp,
  constraint pk_message primary key (id)
);


# --- !Downs

drop table if exists message;

