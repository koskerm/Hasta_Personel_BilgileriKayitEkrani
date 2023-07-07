package com.example.application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.Model.Hasta;
import com.example.application.Presenter.HastaService;

@Component
public class HastaController {

    @Autowired
    private HastaService hastaService;

    public void addHasta(Hasta hasta) {
        hastaService.addHasta(hasta);
    }

    public List<Hasta> findAllHasta(){
        return hastaService.findAllHasta();
    }
    public void deleteHasta(Long hastaTc){
        hastaService.deleteHasta(hastaTc);
    }

    public void updateHasta(Hasta hasta){
        hastaService.updateHasta(hasta);
    }
}

