CREATE DATABASE  IF NOT EXISTS `student`;
USE `student`;

-- Table structure for table `bio`

DROP TABLE IF EXISTS `bio`;

CREATE TABLE `bio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` int(11) NOT NULL,
  
  PRIMARY KEY (`id`)
);

-- Dumping data for table `bio`

INSERT INTO `bio`(name, age) VALUES ('Mickey',100),('Java',20);
