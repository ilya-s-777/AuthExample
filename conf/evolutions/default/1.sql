# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  title                     varchar(255),
  text                      varchar(255),
  due_date                  timestamp,
  constraint pk_post primary key (id))
;

create sequence post_seq;




# --- !Downs

drop table if exists post cascade;

drop sequence if exists post_seq;

