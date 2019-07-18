CREATE TABLE `parking_lot` (
    `name` VARCHAR(20) PRIMARY KEY,
    `capacity` int CHECK (`capacity`>= 0),
    `location` VARCHAR(40)
)