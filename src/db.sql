CREATE DATABASE IF NOT EXISTS test;
USE test;

CREATE TABLE students (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50),
                          age INT
);
