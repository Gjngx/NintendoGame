package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {
}
