package com.example.Buoi7_KT.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "phongban")
public class Phongban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ma_phong;

    @Column(name = "Ten_Phong")
    private String ten_phong;

    @OneToMany(mappedBy = "phongban",cascade = CascadeType.ALL)
    private List<Nhanvien> nhanviens;
}