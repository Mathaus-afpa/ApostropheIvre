-- Création de la base de données
DROP DATABASE IF EXISTS ApostropheIvre;
CREATE DATABASE ApostropheIvre;
USE ApostropheIvre;

-- Création de la table CLIENT
CREATE TABLE CLIENT (
    cli_id INT PRIMARY KEY AUTO_INCREMENT,
    cli_email VARCHAR(100) NOT NULL,
    cli_nom VARCHAR(50) NOT NULL,
    cli_adresse VARCHAR(255),
    cli_code_postal VARCHAR(5),
    cli_ville VARCHAR(50),
    cli_prenom VARCHAR(50) NOT NULL,
    UNIQUE (cli_email)
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
    cat_libelle VARCHAR(50) NOT NULL,
    UNIQUE (cat_libelle)
);

-- Création de la table AUTEUR
CREATE TABLE AUTEUR (
    aut_id INT PRIMARY KEY AUTO_INCREMENT,
    aut_nom VARCHAR(50) NOT NULL,
    aut_prenom VARCHAR(50),
    aut_photo VARCHAR(255)
);

-- Création de la table LIVRE
CREATE TABLE LIVRE (
    liv_id INT PRIMARY KEY AUTO_INCREMENT,
    liv_titre VARCHAR(100) NOT NULL,
    liv_resume TEXT,
    liv_image VARCHAR(255),
    liv_isbn VARCHAR(13),
    liv_quantite INT NOT NULL DEFAULT 0,
    cat_id INT,
    aut_id INT,
    FOREIGN KEY (cat_id) REFERENCES CATEGORIE(cat_id) ON DELETE SET NULL,
    FOREIGN KEY (aut_id) REFERENCES AUTEUR(aut_id) ON DELETE CASCADE,
    CHECK (liv_quantite >= 0),
    UNIQUE (liv_isbn)
);

-- Création de la table EMPRUNTER
CREATE TABLE EMPRUNTER (
    cli_id INT,
    liv_id INT,
    lib_id INT,
    emp_date_emprunt DATETIME DEFAULT CURRENT_TIMESTAMP,
    emp_date_retour DATETIME,
    PRIMARY KEY (cli_id, liv_id, lib_id, emp_date_emprunt),
    FOREIGN KEY (cli_id) REFERENCES CLIENT(cli_id) ON DELETE CASCADE,
    FOREIGN KEY (liv_id) REFERENCES LIVRE(liv_id) ON DELETE CASCADE,
    FOREIGN KEY (lib_id) REFERENCES LIBRAIRE(lib_id) ON DELETE CASCADE
);

-- Trigger pour empêcher la suppression d'une catégorie avec des livres
DELIMITER //
CREATE TRIGGER before_category_delete
BEFORE DELETE ON CATEGORIE
FOR EACH ROW
BEGIN
    DECLARE book_count INT;
    SELECT COUNT(*) INTO book_count FROM LIVRE WHERE cat_id = OLD.cat_id;
    IF book_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Impossible de supprimer une catégorie contenant des livres.';
    END IF;
END//
DELIMITER ;

-- Table d'audit
CREATE TABLE AUDIT_SUPPRESSION (
    aud_id INT PRIMARY KEY AUTO_INCREMENT,
    aud_table_name VARCHAR(50) NOT NULL,
    aud_record_id INT NOT NULL,
    aud_deleted_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    aud_deleted_by VARCHAR(50)
);

-- Triggers pour l'audit
DELIMITER //
CREATE TRIGGER after_client_delete
AFTER DELETE ON CLIENT
FOR EACH ROW
BEGIN
    INSERT INTO AUDIT_SUPPRESSION (aud_table_name, aud_record_id)
    VALUES ('CLIENT', OLD.cli_id);
END//

CREATE TRIGGER after_livre_delete
AFTER DELETE ON LIVRE
FOR EACH ROW
BEGIN
    INSERT INTO AUDIT_SUPPRESSION (aud_table_name, aud_record_id)
    VALUES ('LIVRE', OLD.liv_id);
END//
DELIMITER ;