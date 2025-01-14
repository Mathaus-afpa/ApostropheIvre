USE apostropheivre;
-- Insertion des clients avec des adresses basées sur des monuments de Nancy
INSERT INTO CLIENT (cli_email, cli_nom, cli_prenom, cli_adresse, cli_code_postal, cli_ville)
VALUES
    ('lou.cnt@apostropheivre.com', 'Cnt', 'Lou', 'Place Stanislas', '54000', 'Nancy'),
    ('fab.gne@apostropheivre.com', 'Gne', 'Fab', 'Parc de la Pépinière', '54000', 'Nancy'),
    ('ann.slm@apostropheivre.com', 'Slm', 'Ann', 'Place de la Carrière', '54000', 'Nancy'),
    ('mat.hru@apostropheivre.com', 'Hru', 'Mat', 'Musée des Beaux-Arts', '54000', 'Nancy'),
    ('elise.dupont@apostropheivre.com', 'Dupont', 'Elise', 'Place de la République', '54000', 'Nancy'),
    ('jean.martin@apostropheivre.com', 'Martin', 'Jean', 'Parc de la Pépinière', '54000', 'Nancy'),
    ('pierre.lenoir@apostropheivre.com', 'Lenoir', 'Pierre', 'Porte de la Craffe', '54000', 'Nancy'),
    ('lucie.brunet@apostropheivre.com', 'Brunet', 'Lucie', 'Place de l\'Étoile', '54000', 'Nancy'),
    ('marc.durand@apostropheivre.com', 'Durand', 'Marc', 'Place des Vosges', '54000', 'Nancy'),
    ('sophie.lemoine@apostropheivre.com', 'Lemoine', 'Sophie', 'Boulevard Joffre', '54000', 'Nancy'),
    ('nicolas.martel@apostropheivre.com', 'Martel', 'Nicolas', 'Place de l\'Alliance', '54000', 'Nancy'),
    ('manon.petit@apostropheivre.com', 'Petit', 'Manon', 'Rue des Tiercelins', '54000', 'Nancy'),
    ('olivier.morand@apostropheivre.com', 'Morand', 'Olivier', 'Place Dombasle', '54000', 'Nancy'),
    ('christine.roux@apostropheivre.com', 'Roux', 'Christine', 'Rue Saint-Dizier', '54000', 'Nancy'),
    ('michel.morel@apostropheivre.com', 'Morel', 'Michel', 'Place Carnot', '54000', 'Nancy'),
    ('audrey.bellamy@apostropheivre.com', 'Bellamy', 'Audrey', 'Quai Sainte-Catherine', '54000', 'Nancy'),
    ('louis.grand@apostropheivre.com', 'Grand', 'Louis', 'Place des Chartriers', '54000', 'Nancy');
-- Insertion des catégories
INSERT INTO CATEGORIE (cat_libelle) VALUES
    ('Roman'),
    ('Santé & Bien-être'),
    ('Horreur'),
    ('Développement Personnel'),
    ('Science-Fiction'),
    ('Cuisine'),
    ('Biographie'),
    ('Thriller'),
    ('Fantasy'),
    ('Politique'),
    ('Art & Design'),
    ('Histoire'),
    ('Poésie'),
    ('Romance'),
    ('Mystère'),
    ('Environnement'),
    ('Technologie');
-- Insertion des auteurs
INSERT INTO AUTEUR (aut_nom, aut_prenom, aut_photo)
VALUES
('Ishiguro', 'Kazuo', 'Kazuo_Ishiguro.jpg'),
('Feuerstein', 'Georg', 'Georg_Feuerstein.jpg'),
('Lovecraft', 'Howard Phillips', 'Howard_Phillips_Lovecraft.jpg'),
('Werber', 'Bernard', 'Bernard_Werber.jpg'),
('Rowling', 'J.K.', 'JK_Rowling.jpg'),
('King', 'Stephen', 'Stephen_King.jpg'),
('Duras', 'Marguerite', 'Marguerite_Duras.jpg'),
('Camus', 'Albert', 'Albert_Camus.jpg'),
('Hemingway', 'Ernest', 'Ernest_Hemingway.jpg'),
('Fitzgerald', 'F. Scott', 'F_Scott_Fitzgerald.jpg'),
('Tolstoy', 'Leo', 'Leo_Tolstoy.jpg'),
('Huxley', 'Aldous', 'Aldous_Huxley.jpg'),
('Salinger', 'J.D.', 'JD_Salinger.jpg'),
('Dickens', 'Charles', 'Charles_Dickens.jpg'),
('Proust', 'Marcel', 'Marcel_Proust.jpg'),
('Orwell', 'George', 'George_Orwell.jpg'),
('Austen', 'Jane', 'Jane_Austen.jpg'),
('Flaubert', 'Gustave', 'Gustave_Flaubert.jpg'),
('Borges', 'Jorge Luis', 'Jorge_Luis_Borges.jpg'),
('Defoe', 'Daniel', 'Daniel_Defoe.jpg');
-- Insertion des livres
INSERT INTO LIVRE (liv_titre, liv_resume, liv_image, liv_isbn, liv_quantite, cat_id, aut_id)
VALUES
    ('Auprès de moi toujours', 'Kath, Ruth et Tommy ont été élèves à Hailsham dans les années quatre-vingt-dix...', '2070341925.jpg', '2070341925', 10, 1, 1),
    ('Le Yoga pour les Nuls', 'Ce livre vous aidera à explorer votre propre esprit et à découvrir les bienfaits du yoga...', '2754000631.jpg', '2754000631', 5, 2, 2),
    ('Les Montagnes Hallucinées', 'Un récit captivant où Lovecraft nous plonge dans un monde ...', '2757851357.jpg', '2757851357', 3, 3, 3),
    ('Le Livre du Voyage', 'Imaginez un livre qui vous aide à explorer votre propre esprit et vous emmène en voyage...', '2226094458.jpg', '2226094458', 7, 4, 4);
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
    ('Marivaux', 'Pierre'),
    ('Hugo', 'Victor'),
    ('Zola', 'Émile'),
    ('Proust', 'Marcel'),
    ('Beckett', 'Samuel'),
    ('Molière', 'Jean-Baptiste'),
    ('Bovary', 'Flaubert'),
    ('Dostoevsky', 'Fiodor'),
    ('Sartre', 'Jean-Paul'),
    ('Balzac', 'Honoré de'),
    ('Dumas', 'Alexandre'),
    ('Verne', 'Jules'),
    ('Gide', 'André'),
    ('Flaubert', 'Gustave'),
    ('Gauguin', 'Paul'),
    ('Lafayette', 'Laure de');