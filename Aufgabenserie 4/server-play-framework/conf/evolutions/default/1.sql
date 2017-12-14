# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table entry (
  id                            integer not null,
  version                       integer,
  author                        varchar(255),
  message                       varchar(255),
  creation                      timestamp,
  constraint pk_entry primary key (id)
);

create table message (
  id                            integer not null,
  version                       integer,
  author                        varchar(255),
  message                       varchar(255),
  creation                      timestamp,
  constraint pk_message primary key (id)
);


# --- !Downs

drop table if exists entry;

drop table if exists message;

