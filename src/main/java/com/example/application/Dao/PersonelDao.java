package com.example.application.Dao;

import java.util.List;

import com.example.application.Model.Personel;

public interface PersonelDao {
    void addPersonel(Personel personel);

    void deletePersonel(Long personelTc);

    void updatePersonel(Personel personel);

    Personel getPersonel(Long Tc);

    List<Personel> findAllPersonel();
}
