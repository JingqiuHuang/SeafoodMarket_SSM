drop database if exists seafood;

create database seafood;

use seafood;

create table t_user
(
    `id`       int primary key auto_increment,
    `username` varchar(20) not null unique,
    `password` varchar(32) not null,
    `email`    varchar(200),
    `role`     int         not null
);

insert into t_user(`username`, `password`, `email`, `role`)
values ('admin', 'admin', 'admin@atguigu.com', 0);

select *
from t_user;



create table t_seafood
(
    `id`       int primary key auto_increment, /*海鲜id*/
    `name`     varchar(100), /*海鲜名称*/
    `provider` varchar(100), /*海鲜供应商名称*/
    `price`    decimal(11, 2), /*海鲜价格*/
    `kind`     varchar(100), /*海鲜品种*/
    `sales`    int, /*海鲜销量*/
    `stock`    int, /*海鲜库存*/
    `img_path` varchar(200) /*海鲜图片地址*/
);


## 插入初始化测试数据
insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '大闸蟹', '海鲜供应商A', 120.50, '蟹类', 100, 50, 'static/img/da_zhai_xie.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '青口贝', '海鲜供应商B', 35.00, '贝类', 200, 80, 'static/img/qing_kou_bei.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '龙虾', '海鲜供应商C', 250.00, '虾类', 50, 30, 'static/img/long_xia.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '三文鱼', '海鲜供应商A', 180.00, '鱼类', 150, 60, 'static/img/san_wen_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '鲍鱼', '海鲜供应商D', 300.00, '贝类', 70, 20, 'static/img/bao_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '生蚝', '海鲜供应商E', 45.00, '贝类', 250, 100, 'static/img/sheng_hao.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '鱿鱼', '海鲜供应商F', 60.00, '鱼类', 300, 150, 'static/img/you_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '石斑鱼', '海鲜供应商G', 200.00, '鱼类', 120, 40, 'static/img/shi_ban_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '扇贝', '海鲜供应商H', 80.00, '贝类', 180, 70, 'static/img/shan_bei.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '海参', '海鲜供应商I', 400.00, '参类', 30, 10, 'static/img/hai_shen.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '小龙虾', '海鲜供应商J', 90.00, '虾类', 500, 200, 'static/img/xiao_long_xia.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '黄花鱼', '海鲜供应商K', 75.00, '鱼类', 400, 180, 'static/img/huang_hua_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '海蜇', '海鲜供应商L', 55.00, '其他', 350, 150, 'static/img/hai_zhe.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '螃蟹', '海鲜供应商M', 110.00, '蟹类', 220, 90, 'static/img/pang_xie.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '花蛤', '海鲜供应商N', 40.00, '贝类', 600, 300, 'static/img/hua_ge.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '章鱼', '海鲜供应商O', 150.00, '其他', 180, 70, 'static/img/zhang_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '墨鱼', '海鲜供应商P', 85.00, '其他', 250, 120, 'static/img/mo_yu.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '青蟹', '海鲜供应商Q', 130.00, '蟹类', 160, 60, 'static/img/qing_xie.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '斑节对虾', '海鲜供应商R', 200.00, '虾类', 140, 80, 'static/img/ban_jie_dui_xia.jpg');

insert into t_seafood(`id`, `name`, `provider`, `price`, `kind`, `sales`, `stock`, `img_path`)
values (null, '鲶鱼', '海鲜供应商S', 65.00, '鱼类', 330, 200, 'static/img/nian_yu.jpg');


## 查看表内容
select id,
       name,
       provider,
       price,
       kind,
       sales,
       stock,
       img_path
from t_seafood;



use seafood;

create table t_cart
(
    id      int primary key auto_increment,
    user_id int
);

create table t_cart_item
(
    id          int primary key auto_increment,
    seafood_id  int,
    name        varchar(255),
    count       int,
    price       decimal(11, 2),
    total_price decimal(11, 2),
    cart_id     int,
    foreign key (`cart_id`) references t_cart (`id`)
);

CREATE TABLE t_order
(
    `order_id`    VARCHAR(50) PRIMARY KEY,
    `create_time` DATETIME,
    `total_money` DECIMAL(11, 2),
    `status`      INT NOT NULL DEFAULT 0,
    `user_id`     INT,
    FOREIGN KEY (`user_id`) REFERENCES t_user (`id`)
);

CREATE TABLE t_order_item
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(100),
    `count`       INT,
    price         DECIMAL(11, 2),
    `total_money` DECIMAL(11, 2),
    `order_id`    VARCHAR(50),
    `provider`    VARCHAR(255),
    `kind`        VARCHAR(255),
    `seafood_id`  INT,
    FOREIGN KEY (`order_id`) REFERENCES t_order (`order_id`)
);

