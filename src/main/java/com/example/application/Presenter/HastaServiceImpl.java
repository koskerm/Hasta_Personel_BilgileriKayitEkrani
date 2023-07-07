package com.example.application.Presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.Dao.HastaDao;
import com.example.application.Model.Hasta;

@Service
public class HastaServiceImpl implements HastaService{

    @Autowired
    private HastaDao hastaDao;

    @Override
    public void addHasta(Hasta hasta) {
        if(isValidTC(hasta.getHastaTc())==true){
            hastaDao.addHasta(hasta);
        }else{
            System.out.println("Lütfen geçerli bir TC giriniz");
        }
    }
    private boolean isValidTC(Long hastaTc){
        //hastaTC validation kontrolleri
        boolean validTC =true;
        return validTC;
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
        return hastaDao.findAllHasta();
    }
    @Override
    public void deleteHasta(Long Tc) {
        hastaDao.deleteHasta(Tc);
        
        
    }
}
    

