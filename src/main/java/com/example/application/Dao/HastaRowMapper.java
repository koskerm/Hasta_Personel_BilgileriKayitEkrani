package com.example.application.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.application.Model.Hasta;

public class HastaRowMapper implements RowMapper<Hasta> {

    @Override
    public Hasta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hasta hasta=new Hasta();
        hasta.setHastaTc(rs.getLong("hastaTc"));
        hasta.setHastaAd(rs.getString("hastaAd"));
        hasta.setHastaSoyad(rs.getString("hastaSoyad"));
        hasta.setHastaTel(rs.getString("hastaTelefon"));
        hasta.setHastaEmail(rs.getString("hastaEmail"));
        hasta.setHastaAdres(rs.getString("hastaAdres"));
        hasta.setHastaKan(rs.getString("hastaKan"));
        hasta.setCinsiyet(rs.getString("cinsiyet"));
        return hasta;
    }
}
