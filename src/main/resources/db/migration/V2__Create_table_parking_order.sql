CREATE TABLE `parking_order` (
    `id` BIGINT auto_increment PRIMARY KEY,
    `name` VARCHAR (40),
    `number` CHAR (8),
    `start_time` TIMESTAMP,
    `end_time` TIMESTAMP,
    `state` BIT default TRUE,
    `parking_lot` VARCHAR (20)
);