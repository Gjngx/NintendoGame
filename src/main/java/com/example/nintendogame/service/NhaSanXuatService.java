package com.example.nintendogame.service;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.reponsitory.NhaSanXuatReponsity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaSanXuatService {
    @Autowired
    private NhaSanXuatReponsity nhaSanXuatReponsity;
    public List<NhaSanXuat>GetAllNhaSanXuatAdmin(){
        return nhaSanXuatReponsity.findAll();
    }

    public NhaSanXuat getNhaSanXuatById(Long id){
        Optional<NhaSanXuat> optional = nhaSanXuatReponsity.findById(id);
        return optional.orElse(null);
    }

    public void addnhasanxuat(NhaSanXuat nhaSanXuat){
        nhaSanXuatReponsity.save(nhaSanXuat);
    }

    public void updateNhaSanXuat(NhaSanXuat nhaSanXuat){
        nhaSanXuatReponsity.save(nhaSanXuat);
    }
    public void deleteNhaSanXuat(Long id){nhaSanXuatReponsity.deleteById(id);
    }


}
