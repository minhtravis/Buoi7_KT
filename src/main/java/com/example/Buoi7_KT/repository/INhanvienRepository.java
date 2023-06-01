package com.example.Buoi7_KT.repository;

import com.example.Buoi7_KT.entity.Nhanvien;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface INhanvienRepository extends JpaRepository<Nhanvien,Long> {

}
