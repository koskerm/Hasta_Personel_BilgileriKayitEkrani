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
public class Hasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement'ı temsil eden enum IDENTITFY
    
    
    @Column(name = "hastaTc", columnDefinition = "BIGINT")
    private Long hastaTc;

    @Column(name="hastaAd")
    private String hastaAd;

    @Column(name="hastaSoyad")
    private String hastaSoyad;

    @Column(name="hastaTelefon")
    private String hastaTel;

    @Column(name="hastaEmail")
    private String hastaEmail;

    @Column(name="hastaAdres")
    private String hastaAdres;

    @Column(name="hastaKan")
    private String hastaKan;

    @Column(name="cinsiyet")
    private String cinsiyet;
    


    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public Long getHastaTc() {
        return this.hastaTc;
    }

    public void setHastaTc(Long hastaTc) {
        this.hastaTc = hastaTc;
    }

    public String getHastaAd() {
        return this.hastaAd;
    }

    public void setHastaAd(String hastaAd) {
        this.hastaAd = hastaAd;
    }

    public String getHastaSoyad() {
        return this.hastaSoyad;
    }

    public void setHastaSoyad(String hastaSoyad) {
        this.hastaSoyad = hastaSoyad;
    }

    public String getHastaTel() {
        return this.hastaTel;
    }

    public void setHastaTel(String hastaTel) {
        this.hastaTel = hastaTel;
    }

    public String getHastaEmail() {
        return this.hastaEmail;
    }

    public void setHastaEmail(String hastaEmail) {
        this.hastaEmail = hastaEmail;
    }

    public String getHastaAdres() {
        return this.hastaAdres;
    }

    public void setHastaAdres(String hastaAdres) {
        this.hastaAdres = hastaAdres;
    }

    public String getHastaKan() {
        return this.hastaKan;
    }

    public void setHastaKan(String hastaKan) {
        this.hastaKan = hastaKan;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

}

