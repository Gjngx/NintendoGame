package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamReponsitory extends JpaRepository<SanPham, Long> {
    Page<SanPham> findAllByOrderByNgaydangsanphamDesc(Pageable pageable);
    Page<SanPham> findByTheLoaiId(Long theLoaiId, Pageable pageable);
    Page<SanPham> findByNhaSanXuatId(Long nhaSanXuatId, Pageable pageable);
    List<SanPham> findBySanphamContainingIgnoreCase(String name);
}
