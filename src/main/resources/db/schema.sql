-- schema PUBLIC, H2

drop table if exists `customer`;
drop table if exists `customer_address`;
drop table if exists `seller`;
drop table if exists `product`;
drop table if exists `product_review_summary`;
drop table if exists `category`;
drop table if exists `product_category`;
drop table if exists `order`;
drop table if exists `order_history`;
drop table if exists `order_item`;
drop table if exists `payment`;
drop table if exists `payment_history`;
drop table if exists `delivery`;
drop table if exists `wishlist`;
drop table if exists `product_option`;
drop table if exists `review`;
drop table if exists `sales_product`;
drop table if exists `sales_group`;
drop table if exists `pg_pay_history`;

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

create table `customer_address`
(
    `customer_address_id`  bigint primary key auto_increment,
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
    `product_id`   bigint primary key auto_increment,
    `seller_id`    bigint       not null,
    `name`         varchar(255) not null,
    `description`  varchar(255) not null,
    `image_url`    varchar(255) not null,
    `created_date` datetime     not null,
    `updated_date` datetime     not null
);

create table `category`
(
    `category_id`  bigint primary key auto_increment,
    `parent_id`    bigint,
    `depth`        bigint              not null,
    `name`         varchar(255) unique not null,
    `created_date` datetime            not null,
    `updated_date` datetime            not null
);

create table `product_category`
(
    `product_category_id` bigint auto_increment,
    `product_id`          bigint   not null,
    `category_id`         bigint   not null,
    `created_date`        datetime not null,
    `updated_date`        datetime not null,
    primary key (`product_category_id`),
    unique (`product_id`, `category_id`)
);

create table `order`
(
    `order_id`            bigint primary key auto_increment,
    `customer_address_id` bigint   not null,
    `order_date`          datetime not null,
    `created_date`        datetime not null,
    `updated_date`        datetime not null
);

create table `order_history`
(
    `order_history_id` bigint primary key auto_increment,
    `order_id`         bigint                                                             not null,
    `order_status`     enum ('PENDING', 'SHIPPED', 'IN_TRANSIT', 'COMPLETED', 'CANCELED') not null,
    `created_date`     datetime                                                           not null
);

create table `order_item`
(
    `order_item_id`    bigint primary key auto_increment,
    `order_id`         bigint   not null,
    `sales_product_id` bigint   not null,
    `quantity`         bigint   not null,
    `price_per_item`   bigint   not null,
    `created_date`     datetime not null,
    `updated_date`     datetime not null
);

create table `payment`
(
    `payment_id`   bigint primary key auto_increment,
    `order_id`     bigint unique not null,
    `created_date` datetime      not null,
    `updated_date` datetime      not null
);

create table `payment_history`
(
    `payment_history_id` bigint primary key auto_increment,
    `payment_id`         bigint                                                  not null,
    `amount`             bigint                                                  not null,
    `payment_method`     enum ('CREDIT_CARD', 'KAKAOPAY', 'NAVERPAY', 'TOSSPAY') not null,
    `payment_status`     enum ('WAITING', 'REQUESTING', 'COMPLETED', 'FAILED', 'REFUNDED')     not null,
    `created_date`       datetime                                                not null
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

create table `product_review_summary`
(
    `product_review_summary_id` bigint primary key auto_increment,
    `product_id`                bigint   not null,
    `average_review_rating`     bigint   null,
    `total_review_count`        bigint   null,
    `created_date`              datetime not null,
    `updated_date`              datetime not null
);


create table `wishlist`
(
    `wishlist_id`  bigint primary key auto_increment,
    `product_id`   bigint   not null,
    `customer_id`  bigint   not null,
    `created_date` datetime not null,
    `updated_date` datetime not null
);

CREATE TABLE `product_option`
(
    `product_option_id` BIGINT PRIMARY KEY auto_increment,
    `product_id`        BIGINT       not null,
    `name`              VARCHAR(255) NOT NULL,
    `created_date`      DATETIME     NOT NULL,
    `updated_date`      DATETIME     NOT NULL
);

create table `sales_product`
(
    `sales_product_id`  bigint primary key auto_increment,
    `sales_group_id`    bigint                                               not null,
    `product_option_id` bigint                                               not null,
    `sales_price`       bigint                                               NOT NULL,
    `sales_stock`       BIGINT                                               NOT NULL,
    `sales_status`      enum ('PENDING', 'ON_SALES', 'SOLD_OUT', 'DISCOUNT') not null,
    `created_date`      datetime                                             not null,
    `updated_date`      datetime                                             not null
);

create table `sales_group`
(
    `sales_group_id` bigint primary key auto_increment,
    `created_date`   datetime not null,
    `updated_date`   datetime not null
);

create table `pg_pay_history`
(
    `pg_pay_history_id`               bigint primary key auto_increment,
    `pg_payment_id`              VARCHAR(255) NOT NULL,
    `client_id`              VARCHAR(255) NOT NULL,
    `order_id` VARCHAR(255) NOT NULL,
    `user_id`  VARCHAR(255) NOT NULL,
    `payment_method`     enum ('KAKAOPAY', 'NAVERPAY', 'TOSSPAY') not null
);