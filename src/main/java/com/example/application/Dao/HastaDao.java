package com.example.application.Dao;

import java.util.List;

import com.example.application.Model.Hasta;

public interface HastaDao {
    void addHasta(Hasta hasta);

    void deleteHasta(Long hastaTc);

    void updateHasta(Hasta hasta);

    Hasta getHasta(int id);

    List<Hasta> findAllHasta();
}
