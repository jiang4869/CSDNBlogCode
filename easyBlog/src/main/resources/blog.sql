create database blog;
use blog;


create table `blog_user`
(
    `uid`        int           not null primary key auto_increment,
    `userName`   varchar(30)   not null unique,
    `nickName`   nvarchar(100) not null,
    `password` varchar(20) not null,
    `email`      nvarchar(100) unique not null,
    `avatar`     varchar(100)  not null,
    `qq`         varchar(13) default null,
    `createTime` datetime default now(),
    `updateTime` datetime default now()

) charset = utf8;


create table `blog_message`
(
    `mid`             int           not null primary key auto_increment,
    `uid`             int           default null,
    `createTime`      datetime      not null,
    `content`         nvarchar(500) not null,
    `parentMessageId` int  default null,
    `replyMessageId`  int  default null,
    `email`           varchar(30)   not null,
    `userName`        nvarchar(20)  not null,
    `remind`          bool default false,
    constraint foreign key (`uid`) references `blog_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (`parentMessageId`) references `blog_message` (`mid`) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (`replyMessageId`) references `blog_message` (`mid`) ON DELETE CASCADE ON UPDATE RESTRICT
) charset = utf8;

create table `blog_blogType`
(
    `id`       int           not null primary key auto_increment,
    `typeName` nvarchar(100) not null
) charset = utf8;



create table `blog_blog`
(

    `bid`          int           not null primary key auto_increment,
    `uid` int not null,
    `title`        nvarchar(200) not null,
    `content`      mediumblob default null,
    `summary`      nvarchar(300) not null,
    `firstPicture` varchar(500)  not null,
    `views`        int        default 0,
    `createTime`   datetime   default now(),
    `updateTime`   datetime   default now(),
    `published`    bool       default true,
    `isComment`    bool       default true,
    `typeId`       int        default null,
    constraint foreign key (`typeId`) references `blog_blogType` (`id`),
    constraint foreign key (`uid`) references `blog_user` (`uid`),
) charset = utf8;




create table `blog_comment`
(
    `cid`             int           not null primary key auto_increment,
    `uid`             int           not null,
    `bid`             int           not null,
    `createTime`      datetime      not null,
    `content`         nvarchar(500) not null,
    `parentCommentId` int  default null,
    `replyCommentId`  int  default null,
    `email`           varchar(30)   not null,
    `remind`          bool default false,
    constraint foreign key (`uid`) references `blog_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (`parentCommentId`) references `blog_comment` (`cid`) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (`replyCommentId`) references `blog_comment` (`cid`) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (`bid`) references `blog_blog` (`bid`) ON DELETE CASCADE ON UPDATE RESTRICT

) charset = utf8;

create trigger tri_blogTypeDelete
    before delete
    on `blog_blogType`
    for each row
begin
    declare id int;
    select bt.id into id from blog_blogType bt where bt.id = old.id;
    update `blog_blog` set `typeid`=null where `blog_blog`.typeId = id;
end;

-- 暂时删了  加了评论添加有问题
create trigger tri_blogComment
    after insert
    on `blog_comment`
    for each row
begin
    declare bid int;
    declare cid int;
    declare isComment bool;
    select bc.bid, bc.cid into bid,cid from blog_comment bc where bc.bid = new.bid;
    select blog.isComment into isComment from blog_blog blog where blog.bid = bid;
    if (isComment = false) then
        delete from `blog_comment` where `blog_comment`.cid = cid;
    end if;
end;


