CREATE TABLE `parking_lot` (
    `id` BIGINT auto_increment PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL,
    `capacity` int CHECK (`capacity`>= 0),
    `location` VARCHAR(40)
)