package com.example.Buoi7_KT.services;

import com.example.Buoi7_KT.entity.Nhanvien;
import com.example.Buoi7_KT.repository.INhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanvienServices {
    @Autowired
    private INhanvienRepository nhanvienRepository;
    public List<Nhanvien> getAllNhanviens(){
        return nhanvienRepository.findAll();
    }
    public Nhanvien getNhanvienId(Long id){
        return nhanvienRepository.findById(id).orElse(null);
    }
    public void addNhanvien (Nhanvien  nhanvien ){
        nhanvienRepository.save(nhanvien);
    }
    public void deleteNhanvien (Long id){
        nhanvienRepository.deleteById(id);
    }
    public void updateNhanvien (Nhanvien  nhanvien ){ nhanvienRepository.save(nhanvien); }

}
