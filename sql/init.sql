CREATE DATABASE HomeMoney;

USE HomeMoney;

CREATE TABLE Currencies (id INT PRIMARY KEY, displayName VARCHAR(3));
INSERT INTO Currencies VALUES (1, 'UAH'), (2, 'USD'), (3, 'EUR');

CREATE TABLE Users (id VARCHAR(128) PRIMARY KEY, displayName VARCHAR(128));

CREATE TABLE Accounts (id INT PRIMARY KEY, uid VARCHAR(128), cid INT, displayName VARCHAR(40));