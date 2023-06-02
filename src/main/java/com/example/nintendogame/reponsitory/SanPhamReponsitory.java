package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamReponsitory extends JpaRepository<SanPham, Long> {
}
