package com.example.Buoi7_KT.services;

import com.example.Buoi7_KT.entity.Phongban;
import com.example.Buoi7_KT.repository.IPhongbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongbanService {
    @Autowired
    private IPhongbanRepository phongbanRepository;
    public List<Phongban> getAllPhongbans(){
        return phongbanRepository.findAll();
    }
    public Phongban getPhongbanById(Long id){
        return phongbanRepository.findById(id).orElse(null);
    }
    public Phongban savePhongban(Phongban phongban){
        return phongbanRepository.save(phongban);
    }
    public void deletePhongban(Long id){
        phongbanRepository.deleteById(id);
    }
}

