USE apostropheivre;
-- Insertion des clients
INSERT INTO CLIENT (cli_email, cli_nom, cli_prenom)
VALUES 
    ('lou.cnt@apostropheivre.com', 'Cnt', 'Lou'),
    ('fab.gne@apostropheivre.com', 'Gne', 'Fab'),
    ('ann.slm@apostropheivre.com', 'Slm', 'Ann'),
    ('mat.hru@apostropheivre.com', 'Hru', 'Mat');
-- Insertion des catégories
INSERT INTO CATEGORIE (cat_libelle) VALUES ('Roman');
INSERT INTO CATEGORIE (cat_libelle) VALUES ('Santé & Bien-être');
INSERT INTO CATEGORIE (cat_libelle) VALUES ('Horreur');
INSERT INTO CATEGORIE (cat_libelle) VALUES ('Développement Personnel');
-- Insertion des auteurs
INSERT INTO AUTEUR (aut_nom, aut_prenom, aut_photo)
VALUES
('Ishiguro', 'Kazuo', 'Kazuo_Ishiguro.jpg'),
('Feuerstein', 'Georg', 'Georg_Feuerstein.jpg'),
('Lovecraft', 'Howard Phillips', 'Howard_Phillips_Lovecraft.jpg'),
('Werber', 'Bernard', 'Bernard_Werber.jpg');
-- Insertion des livres
INSERT INTO LIVRE (liv_titre, liv_resume, liv_image, liv_quantite, cat_id, aut_id) 
VALUES 
('Auprès de moi toujours', 'Kath, Ruth et Tommy ont été élèves à Hailsham dans les années quatre-vingt-dix...', '2070341925.jpg', 10, 1, 1),
('Le Yoga pour les Nuls', 'Ce livre vous aidera à explorer votre propre esprit et à découvrir les bienfaits du yoga...', '2754000631.jpg', 5, 2, 2),
('Les Montagnes Hallucinées', 'Un récit captivant où Lovecraft nous plonge dans un monde d\'horreur et de mystère...', '2757851357.jpg', 3, 3, 3),
('Le Livre du Voyage', 'Imaginez un livre qui vous aide à explorer votre propre esprit et vous emmène en voyage...', '2226094458.jpg', 7, 4, 4);
-- Insertion des libraires
INSERT INTO Libraire (lib_nom, lib_prenom)
VALUES 
    ('Arouet', 'François-Marie'),
    ('Rousseau', 'Jean-Jacques'),
    ('Diderot', 'Denis'),
    ('De Beaumarchais', 'Pierre-Augustin Caron'),
    ('De Staël', 'Germaine'),
    ('De Laclos', 'Pierre Choderlos'),
    ('De la Mothe-Fénelon', 'François de Salignac'),
    ('Poquelin', 'Jean-Baptiste'),
    ('La Fontaine', 'Jean'),
    ('Marivaux', 'Pierre');