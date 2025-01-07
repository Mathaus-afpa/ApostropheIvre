-- Création de la base de données
CREATE DATABASE ApostropheIvre;
USE ApostropheIvre;

-- Table CLIENT
CREATE TABLE Client (
    cli_id INT AUTO_INCREMENT PRIMARY KEY,
    cli_nom VARCHAR(50) NOT NULL,
    cli_prenom VARCHAR(50) NOT NULL,
    cli_email VARCHAR(50) NOT NULL UNIQUE
);

-- Table LIVRE
CREATE TABLE Livre (
    liv_id INT AUTO_INCREMENT PRIMARY KEY,
    liv_titre VARCHAR(25) NOT NULL,
    liv_resume TEXT,
    liv_image BLOB,
    liv_quantite INT NOT NULL
);

-- Table LIBRAIRE
CREATE TABLE Libraire (
    lib_id INT AUTO_INCREMENT PRIMARY KEY,
    lib_nom VARCHAR(50) NOT NULL,
    lib_prenom VARCHAR(50) NOT NULL
);

-- Table AUTEUR
CREATE TABLE Auteur (
    aut_id INT AUTO_INCREMENT PRIMARY KEY,
    aut_nom VARCHAR(50) NOT NULL,
    aut_photo BLOB
);

-- Table CATEGORIE
CREATE TABLE Categorie (
    cat_id INT AUTO_INCREMENT PRIMARY KEY,
    cat_libelle VARCHAR(50) NOT NULL UNIQUE
);

-- Table COMPTE
CREATE TABLE Compte (
    sec_id INT AUTO_INCREMENT PRIMARY KEY,
    sec_login VARCHAR(10) NOT NULL UNIQUE,
    sec_pass VARCHAR(255) NOT NULL
);

-- Table EMPRUNTER (relation entre Client et Livre)
CREATE TABLE Emprunter (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    cli_id INT NOT NULL,
    liv_id INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE,
    FOREIGN KEY (cli_id) REFERENCES Client(cli_id),
    FOREIGN KEY (liv_id) REFERENCES Livre(liv_id)
);

-- Table ACCES_CLIENT (relation entre Client et Compte)
CREATE TABLE Acces_Client (
    acc_cli_id INT AUTO_INCREMENT PRIMARY KEY,
    cli_id INT NOT NULL,
    sec_id INT NOT NULL,
    FOREIGN KEY (cli_id) REFERENCES Client(cli_id),
    FOREIGN KEY (sec_id) REFERENCES Compte(sec_id)
);

-- Table ACCES_ADMIN (relation entre Libraire et Compte)
CREATE TABLE Acces_Admin (
    acc_adm_id INT AUTO_INCREMENT PRIMARY KEY,
    lib_id INT NOT NULL,
    sec_id INT NOT NULL,
    FOREIGN KEY (lib_id) REFERENCES Libraire(lib_id),
    FOREIGN KEY (sec_id) REFERENCES Compte(sec_id)
);

-- Table ECRIRE (relation entre Auteur et Livre)
CREATE TABLE Ecrire (
    ecrire_id INT AUTO_INCREMENT PRIMARY KEY,
    aut_id INT NOT NULL,
    liv_id INT NOT NULL,
    FOREIGN KEY (aut_id) REFERENCES Auteur(aut_id),
    FOREIGN KEY (liv_id) REFERENCES Livre(liv_id)
);

-- Table TYPE (relation entre Livre et Categorie)
CREATE TABLE Type (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    liv_id INT NOT NULL,
    cat_id INT NOT NULL,
    FOREIGN KEY (liv_id) REFERENCES Livre(liv_id),
    FOREIGN KEY (cat_id) REFERENCES Categorie(cat_id)
);

-- Finalisation
COMMIT;
