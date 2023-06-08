package com.example.nintendogame.service;

import com.example.nintendogame.entity.TrangThai;
import com.example.nintendogame.reponsitory.TrangThaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrangThaiService {
    private TrangThaiRepository trangThaiRepository;
    public List<TrangThai> GetAllTrangThaiAdmin(){
        return trangThaiRepository.findAll();
    }
}
