/* © Dylan Burnett and Tanner McIntyre 2016. All rights reserved. */

CREATE DATABASE IF NOT EXISTS firstapp;
USE firstapp;

DROP TABLE IF EXISTS User;

CREATE TABLE User (
  Email VARCHAR(50) NOT NULL,
  FirstName VARCHAR(50) NOT NULL,
  LastName VARCHAR(50) NOT NULL,
  BookTitle VARCHAR(50) NOT NULL,
  DueDate VARCHAR(50) NOT NULL,
  IsDue BOOLEAN NOT NULL
);

INSERT INTO User 
  (Email, FirstName, LastName, BookTitle, DueDate, IsDue)
VALUES 
  ('pburnett@elon.edu', 'Dylan', 'Burnett', 'Into The Wild', '12-05-2016', false);