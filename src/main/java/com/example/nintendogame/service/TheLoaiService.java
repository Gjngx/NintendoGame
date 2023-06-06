package com.example.nintendogame.service;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.TheLoai;
import com.example.nintendogame.reponsitory.TheLoaiReponsitory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TheLoaiService {
    private TheLoaiReponsitory theLoaiReponsitory;
    public TheLoai getTheLoaiById(Long id){
        return theLoaiReponsitory.findById(id).orElse(null);
    }
}
