CREATE DATABASE hibernate_annotations_database;
USE hibernate_annotations_database;

CREATE TABLE trainer (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(32),
  language   VARCHAR(16) NOT NULL,
  experience INTEGER     NOT NULL
);

CREATE TABLE trainee (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(32) NOT NULL,
  trainer_id BIGINT      REFERENCES hibernate_annotations_database.trainer(id)
);