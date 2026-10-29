CREATE TABLE `books` (
        `id` int NOT NULL AUTO_INCREMENT,
        `author` varchar(150) NOT NULL,
        `price` decimal(10,2) NOT NULL,
        `title` varchar(200) NOT NULL,
        PRIMARY KEY (`id`)
)
