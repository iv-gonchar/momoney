CREATE DATABASE HomeMoney;

USE HomeMoney;

CREATE TABLE Currencies (id INT PRIMARY KEY, display_name VARCHAR(3));
INSERT INTO Currencies VALUES (1, 'UAH'), (2, 'USD'), (3, 'EUR');

CREATE TABLE Users (id VARCHAR(128) PRIMARY KEY, display_name VARCHAR(128));

CREATE TABLE Accounts (id INT PRIMARY KEY, user_id VARCHAR(128), currency_id INT, display_name VARCHAR(40), FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE, FOREIGN KEY (currency_id) REFERENCES Currencies(id) ON DELETE RESTRICT);