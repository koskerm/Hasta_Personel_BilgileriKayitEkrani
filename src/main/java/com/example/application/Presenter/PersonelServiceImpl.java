package com.example.application.Presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.Dao.PersonelDao;
import com.example.application.Model.Personel;

@Service
public class PersonelServiceImpl implements PersonelService{

    @Autowired
    private PersonelDao personelDao;

    @Override
    public void addPersonel(Personel personel) {
        if(isValidTC(personel.getPersonelTc())==true){
            personelDao.addPersonel(personel);
        }else{
            System.out.println("Lütfen geçerli bir TC giriniz");
        }
    }
    private boolean isValidTC(Long personelTc){
        //hastaTC validation kontrolleri
        boolean validTC =true;
        return validTC;
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
        return personelDao.findAllPersonel();
    }
    @Override
    public void deletePersonel(Long Tc) {

            personelDao.deletePersonel(Tc);
    
    }
    
}


