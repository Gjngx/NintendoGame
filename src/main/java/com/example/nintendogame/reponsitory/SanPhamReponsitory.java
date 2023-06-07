package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamReponsitory extends JpaRepository<SanPham, Long> {
    Page<SanPham> findAllByOrderByNgaydangsanphamDesc(Pageable pageable);
    Page<SanPham> findByTheLoaiId(Long categoryId, Pageable pageable);
}
