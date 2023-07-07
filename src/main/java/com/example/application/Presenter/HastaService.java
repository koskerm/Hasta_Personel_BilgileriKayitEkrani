package com.example.application.Presenter;

import java.util.List;

import com.example.application.Model.Hasta;

public interface HastaService {
    void addHasta(Hasta hasta);

    void deleteHasta(Long tc);

    void updateHasta(Hasta hasta);

    Hasta getHasta(int id);

    List<Hasta> findAllHasta();


}
