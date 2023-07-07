package com.example.application.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.application.Model.Personel;

public class PersonelRowMapper implements RowMapper<Personel> {

    @Override
    public Personel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Personel personel=new Personel();
        personel.setPersonelTc(rs.getLong("personelTc"));
        personel.setPersonelAd(rs.getString("personelAd"));
        personel.setPersonelSoyad(rs.getString("personelSoyad"));
        personel.setPersonelTelefon(rs.getString("personelTelefon"));
        personel.setPersonelEmail(rs.getString("personelEmail"));
        personel.setPersonelAdres(rs.getString("personelAdres"));
        personel.setPersonelKanGrup(rs.getString("personelKanGrup"));

        return personel;
    }
}
