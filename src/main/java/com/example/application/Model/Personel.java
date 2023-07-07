package com.example.application.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement'ı temsil eden enum IDENTITFY
    
    
    @Column(name = "personelTc", columnDefinition = "BIGINT")
    private Long personelTc;

    @Column(name="personelAd")
    private String personelAd;

    @Column(name="personelSoyad")
    private String personelSoyad;

    @Column(name="personelTelefon")
    private String personelTelefon;

    @Column(name="personelEmail")
    private String personelEmail;

    @Column(name="personelAdres")
    private String personelAdres;

    @Column(name="personelKanGrup")
    private String personelKanGrup;

    public Long getPersonelTc() {
        return this.personelTc;
    }


    public void setPersonelTc(Long personelTc) {
        this.personelTc = personelTc;
    }

    public String getPersonelAd() {
        return this.personelAd;
    }

    public void setPersonelAd(String personelAd) {
        this.personelAd = personelAd;
    }

    public String getPersonelSoyad() {
        return this.personelSoyad;
    }

    public void setPersonelSoyad(String personelSoyad) {
        this.personelSoyad = personelSoyad;
    }

    public String getPersonelTelefon() {
        return this.personelTelefon;
    }

    public void setPersonelTelefon(String personelTelefon) {
        this.personelTelefon = personelTelefon;
    }

    public String getPersonelEmail() {
        return this.personelEmail;
    }

    public void setPersonelEmail(String personelEmail) {
        this.personelEmail = personelEmail;
    }

    public String getPersonelAdres() {
        return this.personelAdres;
    }

    public void setPersonelAdres(String personelAdres) {
        this.personelAdres = personelAdres;
    }

    public String getPersonelKanGrup() {
        return this.personelKanGrup;
    }

    public void setPersonelKanGrup(String personelKanGrup) {
        this.personelKanGrup = personelKanGrup;
    }


    
    }

    

    
