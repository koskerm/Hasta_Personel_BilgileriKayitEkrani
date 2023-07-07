package com.example.application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.Model.Personel;
import com.example.application.Presenter.PersonelService;

@Component
public class PersonelController {

    @Autowired
    private PersonelService personelService;

    public void addPersonel(Personel personel) {
        personelService.addPersonel(personel);
    }

    public List<Personel> findAllPersonel(){
        return personelService.findAllPersonel();
    }
    public void deletePersonel(Long personelTc){
        personelService.deletePersonel(personelTc);
    }

    public void updatePersonel(Personel personel){
        personelService.updatePersonel(personel);
    }
}

