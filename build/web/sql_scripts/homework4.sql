CREATE DATABASE IF NOT EXISTS firstapp;
USE firstapp;

DROP TABLE IF EXISTS User;

CREATE TABLE User (
  Email VARCHAR(50) NOT NULL,
  FirstName VARCHAR(50) NOT NULL,
  LastName VARCHAR(50) NOT NULL,
  BookTitle VARCHAR(50) NOT NULL
);

INSERT INTO User 
  (Email, FirstName, LastName, BookTitle)
VALUES 
  ('pburnett@elon.edu', 'Dylan', 'Burnett', 'Into The Wild');