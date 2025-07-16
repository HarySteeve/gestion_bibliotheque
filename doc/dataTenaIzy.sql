INSERT INTO auteur (nom, prenom) VALUES
('Hugo', 'Victor'),
('Camus', 'Albert'),
('Rowling', 'J.K.');

INSERT INTO livre (titre, ibn, edition, tag, id_auteur) VALUES
('Les Misérables', '9782070409189', '1862-01-01', 'Littérature classique', 1),
('L''Étranger', '9782070360022', '1942-01-01', 'Philosophie', 2),
('Harry Potter à l''école des sorciers', '9782070643026', '1997-06-26', 'Jeunesse / Fantastique', 3);

-- Les Misérables (id_livre = 1)
INSERT INTO exemplaire (id_livre, numero, ref, date_indispo) VALUES
(1, 1, 'MIS001', NULL),
(1, 2, 'MIS002', NULL),
(1, 3, 'MIS003', NULL);

-- L'Étranger (id_livre = 2)
INSERT INTO exemplaire (id_livre, numero, ref, date_indispo) VALUES
(2, 1, 'ETR001', NULL),
(2, 2, 'ETR002', NULL);

-- Harry Potter à l'école des sorciers (id_livre = 3)
INSERT INTO exemplaire (id_livre, numero, ref, date_indispo) VALUES
(3, 1, 'HAR001', NULL);

INSERT INTO type_pret (libelle) VALUES
('sur place'),
('a emporter');

INSERT INTO profil (description, nb_livre_max, duree_pret, nb_prolongement, duree_prolongement, nb_jour_penalite, nb_reservation_max, duree_jour_reservation_max) VALUES
('Etudiant', 2, 7, 3, 7, 10, 1, 30),
('Enseignant', 3, 9, 5, 9, 9, 2, 30),
('Professionnel', 4, 12, 7, 12, 8, 3, 30);

INSERT INTO bibliothecaire (nom, prenom, mdp, username, email, date_embauche, date_renvoie) VALUES
('Andrianina', 'Tiana', 'admin123', 'tiana.admin', 'b1@gmail.com', '2023-01-01', NULL);

INSERT INTO adherant (id_profil, nom, prenom, dtn, email, mdp) VALUES
-- Étudiants
(1, 'Bensaïd', 'Amine', '2001-03-12', 'a1@example.com', 'feno123'),
(1, 'El Khattabi', 'Sarah', '2002-05-23', 'a2@example.com', 'feno123'),
(1, 'Moujahid', 'Youssef', '2000-09-10', 'a3@example.com', 'feno123'),

-- Enseignants
(2, 'Benali', 'Nadia', '1980-11-05', 'a4@example.com', 'feno123'),
(2, 'Haddadi', 'Karim', '1975-08-17', 'a5@example.com', 'feno123'),
(2, 'Touhami', 'Salima', '1982-01-29', 'a6@example.com', 'feno123'),

-- Professionnels
(3, 'El Mansouri', 'Rachid', '1990-07-08', 'a7@example.com', 'feno123'),
(3, 'Zerouali', 'Amina', '1992-12-14', 'a8@example.com', 'feno123');


INSERT INTO reabonnement (id_adherant, date_reabonnement, date_debut_abonnement, date_fin_abonnement) VALUES
(1, '2025-02-01', '2025-02-01', '2025-07-24'),
(2, '2025-02-01', '2025-02-01', '2025-07-01'),
(3, '2025-04-01', '2025-04-01', '2025-12-01'),
(4, '2025-07-01', '2025-07-01', '2026-07-01'),
(5, '2025-08-01', '2025-08-01', '2026-05-01'),
(6, '2025-07-01', '2025-07-01', '2026-06-01'),
(7, '2025-06-01', '2025-06-01', '2025-12-01'),
(8, '2024-10-01', '2024-10-01', '2025-06-01');


