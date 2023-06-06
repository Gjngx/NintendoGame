package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.NhaSanXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaSanXuatReponsity extends JpaRepository<NhaSanXuat, Long> {
}
