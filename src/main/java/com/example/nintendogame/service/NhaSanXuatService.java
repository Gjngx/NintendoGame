package com.example.nintendogame.service;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.reponsitory.NhaSanXuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaSanXuatService {
    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;
    public List<NhaSanXuat>GetAllNhaSanXuatAdmin(){
        return nhaSanXuatRepository.findAll();
    }
    public NhaSanXuat getNhaSanXuatById(Long id){
        Optional<NhaSanXuat> optional = nhaSanXuatRepository.findById(id);
        return optional.orElse(null);
    }
    public void addnhasanxuat(NhaSanXuat nhaSanXuat){
        nhaSanXuatRepository.save(nhaSanXuat);
    }
    public void updateNhaSanXuat(NhaSanXuat nhaSanXuat){
        nhaSanXuatRepository.save(nhaSanXuat);
    }
    public void deleteNhaSanXuat(Long id){nhaSanXuatRepository.deleteById(id);
    }


}
