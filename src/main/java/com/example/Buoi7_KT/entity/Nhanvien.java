package com.example.Buoi7_KT.entity;

import com.example.Buoi7_KT.validator.annotation.ValidPhongbanId;
import com.example.Buoi7_KT.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="nhanvien")
public class Nhanvien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nv")
    @NotEmpty(message = "Mã nhân viên không được để trống")
    private String ma_nv;

    @Column(name = "ten_nv")
    @NotEmpty(message = "Tên nhân viên không được để trống")
    private String ten_nv;

    @Column(name = "phai")
    private String phai;

    @Column(name = "noi_sinh")
    private String noi_sinh;

    @Column(name = "luong")
    @NotNull(message = "lương bắt buộc")
    private double luong;

    @ManyToOne
    @JoinColumn(name = "maphong")
    @ValidPhongbanId
    private Phongban phongban;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
