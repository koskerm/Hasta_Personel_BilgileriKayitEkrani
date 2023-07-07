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

import com.example.application.Model.Hasta;

@Repository
public class HastaDaoImpl implements HastaDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HastaDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void addHasta(Hasta hasta) {
        String insertQuery = ""
                + "INSERT INTO hastakayit( "
                + "         hastaTc, "
                + "         hastaAd, "
                + "         hastaSoyad, "
                + "         hastaTelefon, "
                + "         hastaEmail, "
                + "         hastaAdres, "
                + "         hastaKan, "
                + "         cinsiyet)"
                + "     VALUES("
                +"             :hastaTc,"
                + "            :hastaAd,"
                + "            :hastaSoyad,"
                + "            :hastaTelefon,"
                + "            :hastaEmail,"
                + "            :hastaAdres,"
                + "            :hastaKan,"
                + "            :cinsiyet);";

        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("hastaNo",hasta.getHastaNo());
        map.put("hastaTc", hasta.getHastaTc());
        map.put("hastaAd", hasta.getHastaAd());
        map.put("hastaSoyad", hasta.getHastaSoyad());
        map.put("hastaTelefon", hasta.getHastaTel());
        map.put("hastaEmail", hasta.getHastaEmail());
        map.put("hastaAdres", hasta.getHastaAdres());
        map.put("hastaKan", hasta.getHastaKan());
        map.put("cinsiyet", hasta.getCinsiyet());

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
    public void updateHasta(Hasta hasta) {
    
        throw new UnsupportedOperationException("Unimplemented method 'updateHasta'");
    }

    @Override
    public Hasta getHasta(int id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'getHasta'");
    }

    @Override
    public List<Hasta> findAllHasta() {
        String query="SELECT h.* FROM kosker.hastakayit h;";
        return namedParameterJdbcTemplate.query(query, new HastaRowMapper());
    }


    @Override
    public void deleteHasta(Long hastaTc) {
        String query="DELETE FROM kosker.hastakayit h WHERE hastaTc=:hastaTc;";
        
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("hastaNo",hasta.getHastaNo());
        map.put("hastaTc", hastaTc);

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
    

