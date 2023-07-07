package com.example.application.Dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.application.Model.Personel;

@Repository
public class PersonelDaoImpl implements PersonelDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonelDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void addPersonel(Personel personel) {
        String insertQuery = ""
                + "INSERT INTO personelkayit( "
                + "         personelTc, "
                + "         personelAd, "
                + "         personelSoyad, "
                + "         personelTelefon, "
                + "         personelEmail, "
                + "         personelAdres, "
                + "         personelKanGrup) "
                + "     VALUES("
                +"             :personelTc,"
                + "            :personelAd,"
                + "            :personelSoyad,"
                + "            :personelTelefon,"
                + "            :personelEmail,"
                + "            :personelAdres,"
                + "            :personelKan);";

        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("hastaNo",hasta.getHastaNo());
        map.put("personelTc", personel.getPersonelTc());
        map.put("personelAd", personel.getPersonelAd());
        map.put("personelSoyad", personel.getPersonelSoyad());
        map.put("personelTelefon", personel.getPersonelTelefon());
        map.put("personelEmail", personel.getPersonelEmail());
        map.put("personelAdres", personel.getPersonelAdres());
        map.put("personelKan", personel.getPersonelKanGrup());

        

        namedParameterJdbcTemplate.execute(insertQuery, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                try {
                    return ps.executeUpdate();
                } catch (SQLException exception) {
                    return exception;
                }
            }
        });

        
    }

    
    @Override
    public void updatePersonel(Personel personel) {
    
        throw new UnsupportedOperationException("Unimplemented method 'updatePersonel'");
    }

    @Override
    public Personel getPersonel(Long Tc) {
    
        throw new UnsupportedOperationException("Unimplemented method 'getPersonel'");
    }

    @Override
    public List<Personel> findAllPersonel() {
        String query="SELECT h.* FROM kosker.personelkayit h;";
        return namedParameterJdbcTemplate.query(query, new PersonelRowMapper());
    }

    

    @Override
    public void deletePersonel(Long personelTc) {
        
        String query="DELETE FROM kosker.personelkayit h WHERE personelTc=:personelTc;";
        
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("hastaNo",hasta.getHastaNo());
        map.put("personelTc", personelTc);

        namedParameterJdbcTemplate.execute(query, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                try {
                    return ps.executeUpdate();
                } catch (SQLException exception) {
                    return exception;
    }

}


});
}

}
    

