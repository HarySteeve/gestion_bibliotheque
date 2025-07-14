drop database if exists bibliotheque;
create database bibliotheque;
use bibliotheque;

CREATE TABLE auteur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE livre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    ibn VARCHAR(100) DEFAULT NULL,
    edition DATE NOT NULL,
    tag VARCHAR(255) DEFAULT NULL,
    id_auteur INT NOT NULL,
    FOREIGN KEY (id_auteur) REFERENCES auteur(id)
);

CREATE TABLE exemplaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    numero INT NOT NULL,
    ref VARCHAR(50) UNIQUE,
    date_indispo DATE DEFAULT NULL,
    FOREIGN KEY (id_livre) REFERENCES livre(id)
);

CREATE TABLE profil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    nb_livre_max INT NOT NULL,
    duree_pret INT NOT NULL,    
    nb_prolongement INT NOT NULL, 
    duree_prolongement INT NOT NULL,  
    nb_jour_penalite INT DEFAULT 1,  
    nb_reservation_max INT NOT NULL,
    duree_jour_reservation_max INT NOT NULL 
);


CREATE TABLE adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profil INT NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    dtn DATE NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    mdp VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_profil) REFERENCES profil(id)
);
CREATE TABLE penalite(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    date_debut DATE,
    date_fin DATE,
    FOREIGN KEY (id_adherant) REFERENCES adherant(id)
);

CREATE TABLE adherant_etat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    nb_livre_prete INT DEFAULT 0,
    nb_prolongement_fait INT DEFAULT 0,
    nb_jour_penalite INT DEFAULT 0,
    nb_reservation_fait INT DEFAULT 0,
    FOREIGN KEY (id_adherant) REFERENCES adherant(id)
);

CREATE TABLE type_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE accessibilite_livre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    id_profil INT,
    id_type_pret INT,
    age_min INT,
    age_max INT,
    FOREIGN KEY (id_livre) REFERENCES livre(id),
    FOREIGN KEY (id_profil) REFERENCES profil(id),
    FOREIGN KEY (id_type_pret) REFERENCES type_pret(id)
);

CREATE TABLE pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    id_type_pret INT NOT NULL,
    date_pris DATE NOT NULL,
    date_rendu DATE DEFAULT NULL,
    cause_penalite TEXT DEFAULT NULL,
    FOREIGN KEY (id_adherant) REFERENCES adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id),
    FOREIGN KEY (id_type_pret) REFERENCES type_pret(id)
);

CREATE TABLE bibliothecaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    mdp VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    date_embauche DATE NOT NULL,
    date_renvoie DATE DEFAULT NULL
);

CREATE TABLE reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_demande_reservation DATE NOT NULL,
    date_reservee DATE NOT NULL,
    date_validation DATE DEFAULT NULL,
    id_bibliothecaire INT DEFAULT NULL,
    FOREIGN KEY (id_adherant) REFERENCES adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id),
    FOREIGN KEY (id_bibliothecaire) REFERENCES bibliothecaire(id)
);

CREATE TABLE resolution_ferie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jours_decalage INT NOT NULL,
    date DATE NOT NULL
);

-- exemple jour, mois, annee
CREATE TABLE unite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE duree_abonnement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    duree INT NOT NULL,
    id_unite INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (id_unite) REFERENCES unite(id)
);

CREATE TABLE reabonnement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    montant_paye DECIMAL(10,2) NOT NULL,
    date_reabonnement DATE NOT NULL,
    date_debut_abonnement DATE NOT NULL,
    date_fin_abonnement DATE NOT NULL,
    FOREIGN KEY (id_adherant) REFERENCES adherant(id)
);

CREATE TABLE frais_reabonnement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profil INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (id_profil) REFERENCES profil(id)
);

CREATE TABLE demande_prolongement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date_demande DATE NOT NULL,
    debut_prolongement DATE NOT NULL,
    fin_prolongement DATE NOT NULL,
    FOREIGN KEY (id_pret) REFERENCES pret(id)
);

