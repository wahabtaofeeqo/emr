/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  taoltech
 * Created: Mar 8, 2024
 */

CREATE TABLE IF NOT EXISTS roles(
    id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL UNIQUE,
	created_at DATETIME(6),
    updated_at DATETIME(6),

    PRIMARY KEY (id)
);

INSERT INTO roles(name) VALUES ("USER");

INSERT INTO roles(name) VALUES ("PATIENT");

INSERT INTO roles(name) VALUES ("DOCTOR");

INSERT INTO roles(name) VALUES ("ADMIN");
