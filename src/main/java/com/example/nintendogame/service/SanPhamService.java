package com.example.nintendogame.service;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.reponsitory.SanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamReponsitory sanPhamReponsitory;
    public List<SanPham> getAllSanPhams(){
        return sanPhamReponsitory.findAll();
    }
    public List<SanPham> getLatestProducts(int count) {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaydangsanpham");
        Pageable pageable = PageRequest.of(0, count, sort);
        return sanPhamReponsitory.findAll(pageable).getContent();
    }
    public Page<SanPham> getSortedAndPagedSanPhams(Pageable pageable) {
        return sanPhamReponsitory.findAllByOrderByNgaydangsanphamDesc(pageable);
    }

}
