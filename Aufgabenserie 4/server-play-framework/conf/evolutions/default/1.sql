# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table message (
  id                            integer not null,
  version                       integer,
  author                        varchar(255),
  message                       varchar(255),
  creation                      timestamp,
  creator                       varchar(255),
  constraint pk_message primary key (id)
);

create table user_info (
  uuid                          varchar(40) not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_user_info primary key (uuid)
);


# --- !Downs

drop table if exists message;

drop table if exists user_info;

