DROP DATABASE IF EXISTS ApostropheIvreLogin;
CREATE DATABASE ApostropheIvreLogin;
USE ApostropheIvreLogin;
-- Création de la table des rôles
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT, -- Identifiant unique pour chaque rôle
    role VARCHAR(50) NOT NULL UNIQUE  -- Nom unique du rôle (administrateur, libraire, client)
);
-- Insertion des rôles prédéfinis
INSERT INTO roles (role) VALUES ('administrateur'), ('libraire');
-- Création de la table des utilisateurs
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- Identifiant unique pour chaque utilisateur
    mail VARCHAR(255) NOT NULL UNIQUE,  -- Adresse mail unique
    login VARCHAR(100) NOT NULL UNIQUE,  -- Nom d'utilisateur unique
    password VARCHAR(255) NOT NULL,  -- Mot de passe hashé
    role INT,  -- Référence au rôle de l'utilisateur
     FOREIGN KEY (role) REFERENCES roles(id) ON DELETE SET NULL
);

-- Insertion des utilisateurs dans la table "users"
INSERT INTO users (mail, login, `password`, `role`) VALUES
    ('admin@apostropheivre.com', 'admin', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 1),
    ('lou.cnt@apostropheivre.com', 'Cnt', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 1),
    ('fab.gne@apostropheivre.com', 'Gne', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 1),
    ('ann.slm@apostropheivre.com', 'Slm', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 1),
    ('mat.hru@apostropheivre.com', 'Hru', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 1),
    ('elise.dupont@apostropheivre.com', 'elise.dupont', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('jean.martin@apostropheivre.com', 'Martin', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('pierre.lenoir@apostropheivre.com', 'Lenoir', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('lucie.brunet@apostropheivre.com', 'Brunet', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('marc.durand@apostropheivre.com', 'Durand', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('sophie.lemoine@apostropheivre.com', 'Lemoine', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('nicolas.martel@apostropheivre.com', 'Martel', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('manon.petit@apostropheivre.com', 'Petit', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('olivier.morand@apostropheivre.com', 'libraire', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('christine.roux@apostropheivre.com', 'lib', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('michel.morel@apostropheivre.com', 'michel.morel', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('audrey.bellamy@apostropheivre.com', 'audrey.bellamy', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('louis.grand@apostropheivre.com', 'louis.grand', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', 2),
    ('client@exemple.com', 'client123', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', NULL),
    ('client2@exemple.com', 'client456', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', NULL),
    ('client3@exemple.com', 'clieny', '$2a$10$C4H4jKYYtw.HeEAGr7hJkeA9.qdEQOLOqw//j/6zveOhtE0/.sbPO', NULL);