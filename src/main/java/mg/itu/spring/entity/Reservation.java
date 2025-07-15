package mg.itu.spring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @Column(name = "date_demande_reservation", nullable = false)
    private LocalDate dateDemandeReservation;

    @Column(name = "date_reservee", nullable = false)
    private LocalDate dateReservee;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @ManyToOne
    @JoinColumn(name = "id_bibliothecaire")
    private Bibliothecaire bibliothecaire;

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public LocalDate getDateDemandeReservation() {
        return dateDemandeReservation;
    }

    public void setDateDemandeReservation(LocalDate dateDemandeReservation) {
        this.dateDemandeReservation = dateDemandeReservation;
    }

    public LocalDate getDateReservee() {
        return dateReservee;
    }

    public void setDateReservee(LocalDate dateReservee) {
        this.dateReservee = dateReservee;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Bibliothecaire getBibliothecaire() {
        return bibliothecaire;
    }

    public void setBibliothecaire(Bibliothecaire bibliothecaire) {
        this.bibliothecaire = bibliothecaire;
    }
}
