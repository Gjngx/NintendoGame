package com.example.nintendogame.service;

import com.example.nintendogame.entity.TheLoai;
import com.example.nintendogame.reponsitory.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    public List<TheLoai> GetAllTheLoaiAdmin(){
        return theLoaiRepository.findAll();
    }
    public Page<TheLoai> getAllAndPaged(Pageable pageable) {
        return theLoaiRepository.findAll(pageable);
    }
    public TheLoai getTheLoaiById(Long id){
        Optional<TheLoai> optional = theLoaiRepository.findById(id);
        return optional.orElse(null);
    }
    public void addTheLoai(TheLoai theLoai){
        theLoaiRepository.save(theLoai);
    }
    public void updateTheLoai(TheLoai theLoai){
        theLoaiRepository.save(theLoai);
    }
    public void deleteTheLoai(Long id){theLoaiRepository.deleteById(id);}
}
