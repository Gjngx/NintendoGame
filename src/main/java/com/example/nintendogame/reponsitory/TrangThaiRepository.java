package com.example.nintendogame.reponsitory;

import com.example.nintendogame.entity.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long> {
}
