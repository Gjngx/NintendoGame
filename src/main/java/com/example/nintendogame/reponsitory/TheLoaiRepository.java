package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {
    List<TheLoai> findByTheloaiContainingIgnoreCase(String name);
}
