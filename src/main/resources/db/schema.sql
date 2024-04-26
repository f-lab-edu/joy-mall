-- schema PUBLIC, H2

drop table if exists `customer`;
drop table if exists `seller`;
drop table if exists `product`;
drop table if exists `category`;
drop table if exists `product_category`;
drop table if exists `order`;
drop table if exists `address`;
drop table if exists `order_item`;
drop table if exists `payment`;
drop table if exists `delivery`;
drop table if exists `wishlist`;
drop table if exists `review`;

create table `customer`
(
    `customer_id`  bigint primary key auto_increment,
    `email`        varchar(255) unique not null,
    `password`     varchar(255)        not null,
    `name`         varchar(20)         not null,
    `phone_number` varchar(20) unique  not null,
    `created_date` datetime            not null,
    `updated_date` datetime            not null
);



create table `seller`
(
    `seller_id`    bigint primary key auto_increment,
    `email`        varchar(255)       not null,
    `password`     varchar(255)       not null,
    `name`         varchar(20)        not null,
    `store_name`   varchar(20)        not null,
    `phone_number` varchar(20) unique not null,
    `created_date` datetime           not null,
    `updated_date` datetime           not null
);

create table `product`
(
    `product_id`     bigint primary key auto_increment,
    `seller_id`      bigint       not null,
    `name`           varchar(255) not null,
    `description`    varchar(255) not null,
    `image_url`      varchar(255) not null,
    `created_date`   datetime     not null,
    `updated_date`   datetime     not null
);

create table `category`
(
    `category_id`  bigint primary key auto_increment,
    `parent_id`    bigint not null,
    `depth`        bigint              not null,
    `name`         varchar(255) unique not null,
    `created_date` datetime            not null,
    `updated_date` datetime            not null
);

create table `product_category`
(
    `product_category_id` bigint auto_increment,
    `product_id`          bigint not null,
    `category_id`         bigint not null,
    `created_date`        datetime not null,
    `updated_date`        datetime not null,
    primary key (`product_category_id`),
    unique (`product_id`, `category_id`)
);

create table `order`
(
    `order_id`     bigint primary key auto_increment,
    `customer_id`  bigint                                                             not null,
    `address_id`  bigint                                                              not null,
    `order_date`   datetime                                                           not null,
    `status`       enum ('PENDING', 'SHIPPED', 'IN_TRANSIT', 'COMPLETED', 'CANCELED') not null,
    `created_date` datetime                                                           not null,
    `updated_date` datetime                                                           not null
);

create table `address`
(
    `address_id`            bigint primary key auto_increment,
    `customer_id`          bigint      not null,
    `receipt_name`         varchar(20) not null,
    `receipt_phone_number` varchar(20) not null,
    `region`               varchar(50) not null,
    `city`                 varchar(50) not null,
    `town`                 varchar(50) not null,
    `street`               varchar(50) not null,
    `zip_code`             varchar(50) not null,
    `detail`               varchar(50) not null,
    `created_date`         datetime    not null,
    `updated_date`         datetime    not null
);

create table `order_item`
(
    `order_item_id`  bigint primary key auto_increment,
    `order_id`       bigint   not null,
    `product_id`     bigint   not null,
    `product_option_id`     bigint   not null,
    `quantity`       bigint   not null,
    `price_per_item` double   not null,
    `created_date`   datetime not null,
    `updated_date`   datetime not null
);

create table `payment`
(
    `payment_id`     bigint primary key auto_increment,
    `order_id`       bigint unique                                           not null,
    `amount`         double                                                  not null,
    `payment_method` enum ('CREDIT_CARD', 'KAKAOPAY', 'NAVERPAY', 'TOSSPAY') not null,
    `payment_date`   datetime                                                not null,
    `payment_status` enum ('WAITING', 'COMPLETED', 'FAILED', 'REFUNDED')     not null,
    `created_date`   datetime                                                not null,
    `updated_date`   datetime                                                not null
);


create table `delivery`
(
    `delivery_id`     bigint primary key auto_increment,
    `order_id`        bigint unique                                                      not null,
    `delivery_notes`  varchar(255)                                                       not null,
    `delivery_status` enum ('PENDING', 'SHIPPED', 'IN_TRANSIT', 'DELIVERED', 'CANCELED') not null,
    `tracking_number` varchar(255)                                                       not null,
    `shipment_date`   datetime                                                           not null,
    `estimated_date`  datetime                                                           not null,
    `arrival_date`    datetime                                                           not null,
    `created_date`    datetime                                                           not null,
    `updated_date`    datetime                                                           not null
);

create table `review`
(
    `review_id`    bigint primary key auto_increment,
    `customer_id`  bigint   not null,
    `product_id`   bigint   not null,
    `content`      text     not null,
    `rating`       int      not null,
    `created_date` datetime not null,
    `updated_date` datetime not null
);


create table `wishlist`
(
    `wishlist_id`  bigint primary key auto_increment,
    `product_id`   bigint not null,
    `customer_id`  bigint not null,
    `created_date` datetime not null,
    `updated_date` datetime not null
);

drop table PRODUCT_OPTION;

CREATE TABLE `PRODUCT_OPTION`
(
    `option_id`      BIGINT PRIMARY KEY auto_increment,
    `product_id`     BIGINT not null,
    `name`           VARCHAR(255) NOT NULL,
    `price`          DOUBLE       NOT NULL,
    `stock_quantity` BIGINT       NOT NULL,
    `created_date`   DATETIME     NOT NULL,
    `updated_date`   DATETIME     NOT NULL
);

