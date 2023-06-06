package com.example.nintendogame.service;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.reponsitory.NhaSanXuatReponsity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NhaSanXuatService {
    @Autowired
    private NhaSanXuatReponsity nhaSanXuatReponsity;
    public List<NhaSanXuat>GetAllNhaSanXuatAdmin(){
        return nhaSanXuatReponsity.findAll();
    }
}
