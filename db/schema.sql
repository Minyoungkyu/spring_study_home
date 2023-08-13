CREATE DATABASE spring_crud_practice;

USE spring_crud_practice;

CREATE TABLE article (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reg_date DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL);


