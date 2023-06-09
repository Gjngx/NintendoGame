package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.NhaSanXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaSanXuatRepository extends JpaRepository<NhaSanXuat, Long> {
    List<NhaSanXuat> findByNsxContainingIgnoreCase(String name);
}
