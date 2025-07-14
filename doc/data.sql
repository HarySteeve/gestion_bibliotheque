USE bibliotheque;


INSERT INTO profil (description, nb_livre_max, duree_pret, nb_prolongement, duree_prolongement, nb_jour_penalite, nb_reservation_max, duree_jour_reservation_max) VALUES
('Etudiant', 3, 15, 2, 7, 1, 2, 20),
('Professeur', 5, 30, 3, 10, 1, 5, 30);


INSERT INTO auteur (nom, prenom) VALUES
('Zola', 'Émile'),
('Hugo', 'Victor'),
('Camus', 'Albert'),
('Dumas', 'Alexandre'),
('Gide', 'André');

INSERT INTO livre (titre, ibn, edition, tag, id_auteur) VALUES
('Germinal', '978-2070360423', '1885-03-01', 'roman,social,realisme', 1),
('Les Miserables', '978-2070409184', '1862-06-30', 'classique,histoire,justice', 2),
('L''etranger', '978-2070360027', '1942-05-01', 'philosophie,absurde', 3),
('Le Comte de Monte-Cristo', '978-2070408651', '1845-08-01', 'aventure,vendetta', 4),
('Les Nourritures terrestres', '978-2070325873', '1897-01-01', 'poetique,voyage,introspection', 5);

INSERT INTO exemplaire (id_livre, numero, ref, date_indispo) VALUES
(1, 101, 'EX-001-A', NULL),
(1, 102, 'EX-002-A', '2025-06-25'),
(2, 201, 'EX-001-B', NULL),
(2, 202, 'EX-002-B', NULL),
(3, 301, 'EX-001-C', '2025-06-15'),
(3, 302, 'EX-002-C', NULL),
(3, 303, 'EX-003-C', NULL);

INSERT INTO adherant (id_profil, nom, prenom, mdp, dtn, email) VALUES
(1, 'Randriamihaja', 'Feno', 'feno123', '2006-02-04', 'a1@gmail.com'),
(2, 'Rakoto', 'Miora', 'miora123', '2004-02-04', 'a2@gmail.com'),
(1, 'Razanajatovo', 'Tiana', 'tiana123', '2005-02-04', 'a3@gmail.com'),
(2, 'Andriamanjato', 'Solo', 'solo123', '2007-02-04', 'a4@gmail.com'),
(1, 'Rasoanaivo', 'Lova', 'lova123', '2003-02-04', 'a5@gmail.com');

INSERT INTO type_pret (libelle) VALUES
('sur place'),
('a emporter');

INSERT INTO accessibilite_livre (id_livre, id_profil, id_type_pret, age_min, age_max) VALUES
(1, 1, 1, 18, 25),

(2, 2, 2, 30, 65);


INSERT INTO unite (libelle) VALUES
('jour'),
('mois'),
('annee');

INSERT INTO duree_abonnement (duree, id_unite, date) VALUES
(6, 2, '2025-01-01'),
(1, 3, '2025-02-01');


INSERT INTO bibliothecaire (nom, prenom, mdp, username, email, date_embauche, date_renvoie) VALUES
('Andrianina', 'Tiana', 'admin123', 'tiana.admin', 'b1@gmail.com', '2023-01-01', NULL);

INSERT INTO frais_reabonnement (id_profil, montant, description, date) VALUES
(1, 5000, 'Abonnement étudiant annuel', '2025-01-01'),
(2, 10000, 'Abonnement professeur annuel', '2025-01-01');
