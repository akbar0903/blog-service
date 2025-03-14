create database if not exists blog;
use blog;

create table if not exists admin(
                                    id int unsigned auto_increment primary key comment '主键',
                                    name varchar(32) comment '管理员昵称',
                                    username varchar(32) not null unique comment '管理员名',
                                    role enum('admin', 'visitor') default 'visitor' comment '管理员角色，admin可以进行后台数据修改，visitor只能访问后台',
                                    password varchar(60) not null comment '密码',
                                    avatar varchar(255) comment '头像',
                                    email varchar(255) comment '邮箱',
                                    qq_number varchar(15) comment 'qq号',
                                    address varchar(60) comment '管理员地址',
                                    github_url varchar(255) comment 'GitHub地址',
                                    bilibili_url varchar(255) comment '哔哩哔哩地址',
                                    gitee_url varchar(255) comment 'Gitee地址',
                                    created_time datetime not null comment '创建时间',
                                    updated_time datetime not null comment '更新时间'
);

create table if not exists tag(
                                  id int unsigned auto_increment primary key comment '主键',
                                  name varchar(20) not null unique comment '标签名',
                                  admin_id int unsigned not null comment '外键，管理员id',
                                  created_time datetime not null comment '创建时间',
                                  updated_time datetime not null comment '更新时间'
);

create table if not exists category(
                                       id int unsigned auto_increment primary key comment '主键',
                                       name varchar(20) not null unique comment '分类名',
                                       admin_id int unsigned not null comment '外键，管理员id',
                                       created_time datetime not null comment '创建时间',
                                       updated_time datetime not null comment '更新时间'
);

create table if not exists article(
                                      id int unsigned auto_increment primary key comment '主键',
                                      title varchar(255) not null unique comment '文章标题',
                                      summary text not null comment '文章摘要',
                                      content longtext not null comment '文章内容',
                                      cover_image varchar(255) comment '文章封面',
                                      state tinyint not null default '0' comment '文章状态，0草稿，1发布',
                                      admin_id int unsigned not null comment '外键，管理员id',
                                      category_id int unsigned not null comment '外键,分类id',
                                      created_time datetime not null comment '创建时间',
                                      updated_time datetime not null comment '更新时间'
);

-- 标签和文章多对多关系
create table if not exists article_tag(
                                          article_id int unsigned not null,
                                          tag_id int unsigned not null,
                                          primary key(article_id, tag_id)
);

create table if not exists image(
                                    id int unsigned auto_increment primary key comment '图片id',
                                    url varchar(255) not null unique comment '图片url',
                                    object_name varchar(255) not null unique comment '图片名',
                                    admin_id int unsigned not null comment '管理员id',
                                    upload_time datetime not null comment '上传时间'
);