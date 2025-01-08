-- Création de la base de données
DROP database if exists ApostropheIvre;
CREATE DATABASE ApostropheIvre;
USE ApostropheIvre;

-- Création de la table CLIENT
CREATE TABLE CLIENT (
    cli_id INT PRIMARY KEY AUTO_INCREMENT,
    cli_adresse VARCHAR(100),
    cli_codePostale VARCHAR(5),
    cli_ville VARCHAR(50),
    cli_email VARCHAR(100) NOT NULL,
    cli_nom VARCHAR(50) NOT NULL,
    cli_prenom VARCHAR(50) NOT NULL
);

-- Création de la table LIBRAIRE
CREATE TABLE LIBRAIRE (
    lib_id INT PRIMARY KEY AUTO_INCREMENT,
    lib_nom VARCHAR(50) NOT NULL,
    lib_prenom VARCHAR(50) NOT NULL
);

-- Création de la table CATEGORIE
CREATE TABLE CATEGORIE (
    cat_id INT PRIMARY KEY AUTO_INCREMENT,
    cat_libelle VARCHAR(50) NOT NULL
);

-- Création de la table AUTEUR
CREATE TABLE AUTEUR (
    aut_id INT PRIMARY KEY AUTO_INCREMENT,
    aut_nom VARCHAR(50) NOT NULL,
    aut_prenom VARCHAR(50),
    aut_photo VARCHAR(50)
);

-- Création de la table LIVRE
CREATE TABLE LIVRE (
    liv_id INT PRIMARY KEY AUTO_INCREMENT,
    liv_titre VARCHAR(100) NOT NULL,
    liv_resume TEXT,
    liv_image VARCHAR(50),
    liv_quantite INT NOT NULL DEFAULT 0,
    cat_id INT,
    aut_id INT,
    FOREIGN KEY (cat_id) REFERENCES CATEGORIE(cat_id),
    FOREIGN KEY (aut_id) REFERENCES AUTEUR(aut_id)
);

-- Création de la table EMPRUNTER
CREATE TABLE EMPRUNTER (
    cli_id INT,
    liv_id INT,
    lib_id INT,
    date_emprunt DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cli_id, liv_id, lib_id),
    FOREIGN KEY (cli_id) REFERENCES CLIENT(cli_id),
    FOREIGN KEY (liv_id) REFERENCES LIVRE(liv_id),
    FOREIGN KEY (lib_id) REFERENCES LIBRAIRE(lib_id)
);

-- Finalisation
COMMIT;
