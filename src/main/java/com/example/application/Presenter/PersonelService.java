package com.example.application.Presenter;

import java.util.List;

import com.example.application.Model.Personel;

public interface PersonelService {
    void addPersonel(Personel personel);

    void deletePersonel(Long Tc);

    void updatePersonel(Personel personel);

    Personel getPersonel(Long Tc);

    List<Personel> findAllPersonel();


}
