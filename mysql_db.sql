/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/21 10:52:17                          */
/*==============================================================*/


drop table if exists directmsgs;

drop table if exists forwards;

drop table if exists likes;

drop table if exists mentions;

drop table if exists pics;

drop table if exists relationships;

drop table if exists replays;

drop table if exists t2p;

drop table if exists t2t;

drop table if exists topics;

drop table if exists tweets;

drop table if exists users;

drop table if exists videos;

/*==============================================================*/
/* Table: directmsgs                                            */
/*==============================================================*/
create table directmsgs
(
   did                  int not null auto_increment,
   huid                 int not null,
   suid                 int not null,
   dcontent             varchar(1000) not null,
   msgtime              datetime not null,
   dstate               tinyint not null default 1,
   primary key (did)
);

/*==============================================================*/
/* Table: forwards                                              */
/*==============================================================*/
create table forwards
(
   fid                  int not null auto_increment,
   tid                  int not null,
   uid                  int not null,
   fcontent             varchar(200) not null,
   forwardtime          datetime not null,
   fstate               tinyint not null default 1,
   primary key (fid)
);

/*==============================================================*/
/* Table: likes                                                 */
/*==============================================================*/
create table likes
(
   lid                  int not null auto_increment,
   tid                  int not null,
   uid                  int not null,
   liketime             datetime not null,
   primary key (lid)
);

/*==============================================================*/
/* Table: mentions                                              */
/*==============================================================*/
create table mentions
(
   mid                  int not null auto_increment,
   uid                  int not null,
   tid                  int not null,
   primary key (mid)
);

/*==============================================================*/
/* Table: pics                                                  */
/*==============================================================*/
create table pics
(
   pid                  int not null auto_increment,
   pname                varchar(200) not null,
   ppath                varchar(200) not null,
   primary key (pid)
);

/*==============================================================*/
/* Table: relationships                                         */
/*==============================================================*/
create table relationships
(
   rsid                 int not null auto_increment,
   huid                 int not null,
   suid                 int not null,
   followtime           datetime not null,
   rsstate              tinyint not null default 1,
   primary key (rsid)
);

/*==============================================================*/
/* Table: replays                                               */
/*==============================================================*/
create table replays
(
   rid                  int not null auto_increment,
   huid                 int not null,
   suid                 int not null,
   tid                  int not null,
   rcontent             varchar(1000) not null,
   replaytime           datetime not null,
   rstate               tinyint not null default 1,
   primary key (rid)
);

/*==============================================================*/
/* Table: t2p                                                   */
/*==============================================================*/
create table t2p
(
   tpid                 int not null,
   tid                  int not null,
   pid                  int not null,
   primary key (tpid)
);

/*==============================================================*/
/* Table: t2t                                                   */
/*==============================================================*/
create table t2t
(
   ttid                 int not null auto_increment,
   tid                  int not null,
   tpid                 int not null,
   primary key (ttid)
);

/*==============================================================*/
/* Table: topics                                                */
/*==============================================================*/
create table topics
(
   tpid                 int not null auto_increment,
   tpcontent            varchar(200) not null,
   primary key (tpid)
);

/*==============================================================*/
/* Table: tweets                                                */
/*==============================================================*/
create table tweets
(
   tid                  int not null auto_increment,
   vid                  int,
   uid                  int not null,
   tcontent             varchar(200) not null,
   tweettime            datetime not null,
   tstate               tinyint not null default 1,
   primary key (tid)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   uid                  int not null auto_increment,
   username             varchar(50) not null,
   password             varchar(50) not null,
   email                varchar(200) not null,
   telphone             varchar(50),
   nickname             varchar(50) not null,
   gender               tinyint,
   birthday             date,
   city                 varchar(200),
   avatar               varchar(200) not null,
   bio                  varchar(1000),
   registtime           datetime not null,
   ustate               tinyint not null default 1,
   primary key (uid)
);

/*==============================================================*/
/* Table: videos                                                */
/*==============================================================*/
create table videos
(
   vid                  int not null auto_increment,
   vname                varchar(200) not null,
   vpath                varchar(200) not null,
   primary key (vid)
);

alter table directmsgs add constraint FK_Relationship_16 foreign key (suid)
      references users (uid) on delete restrict on update restrict;

alter table directmsgs add constraint FK_Relationship_17 foreign key (huid)
      references users (uid) on delete restrict on update restrict;

alter table forwards add constraint FK_Relationship_19 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table forwards add constraint FK_Relationship_20 foreign key (uid)
      references users (uid) on delete restrict on update restrict;

alter table likes add constraint FK_Relationship_11 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table likes add constraint FK_Relationship_12 foreign key (uid)
      references users (uid) on delete restrict on update restrict;

alter table mentions add constraint FK_Relationship_23 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table mentions add constraint FK_Relationship_24 foreign key (uid)
      references users (uid) on delete restrict on update restrict;

alter table relationships add constraint FK_Relationship_2 foreign key (suid)
      references users (uid) on delete restrict on update restrict;

alter table relationships add constraint FK_Relationship_3 foreign key (huid)
      references users (uid) on delete restrict on update restrict;

alter table replays add constraint FK_Relationship_13 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table replays add constraint FK_Relationship_14 foreign key (suid)
      references users (uid) on delete restrict on update restrict;

alter table replays add constraint FK_Relationship_15 foreign key (huid)
      references users (uid) on delete restrict on update restrict;

alter table t2p add constraint FK_Relationship_10 foreign key (pid)
      references pics (pid) on delete restrict on update restrict;

alter table t2p add constraint FK_Relationship_9 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table t2t add constraint FK_Relationship_21 foreign key (tid)
      references tweets (tid) on delete restrict on update restrict;

alter table t2t add constraint FK_Relationship_22 foreign key (tpid)
      references topics (tpid) on delete restrict on update restrict;

alter table tweets add constraint FK_Relationship_18 foreign key (vid)
      references videos (vid) on delete restrict on update restrict;

alter table tweets add constraint FK_Relationship_6 foreign key (uid)
      references users (uid) on delete restrict on update restrict;

