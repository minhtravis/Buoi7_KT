package com.example.Buoi7_KT.controller;

import com.example.Buoi7_KT.entity.Nhanvien;
import com.example.Buoi7_KT.services.NhanvienServices;
import com.example.Buoi7_KT.services.PhongbanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/nhanviens")
public class NhanvienController {
    @Autowired
    private NhanvienServices nhanvienServices;

    @Autowired
    private PhongbanService phongbanService;

    @GetMapping
    public String showAllNhanviens(Model model){
        List<Nhanvien> nhanviens = nhanvienServices.getAllNhanviens();
        model.addAttribute("nhanviens",nhanviens);
        return "nhanvien/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchText") String searchText,Model model) {
        List<Nhanvien> nhanviens = nhanvienServices.getAllNhanviens();
        List<Nhanvien> filteredNhanviens = new ArrayList<>();

        if (searchText != null && !searchText.isEmpty()) {
            filteredNhanviens = nhanviens.stream()
                    .filter(nhanvien -> nhanvien.getTen_nv().contains(searchText))
                    .collect(Collectors.toList());
        }
        model.addAttribute("nhanviens", filteredNhanviens);
        return "nhanvien/list";
    }

    @GetMapping("/add")
    public String addNhanvienForm(Model model){
        model.addAttribute("nhanvien", new Nhanvien());
        model.addAttribute("phongbans",phongbanService.getAllPhongbans());
        return "nhanvien/add";
    }
    @PostMapping("/add")
    public String addNhanvien(@Valid @ModelAttribute("nhanvien") Nhanvien nhanvien, @RequestParam("imageProduct") MultipartFile imageProduct, BindingResult result, Model model){
        // check lỗi
        if(result.hasErrors()){
            model.addAttribute("phongbans",phongbanService.getAllPhongbans());
            return "nhanvien/add";
        }
        else {
            if (imageProduct != null && imageProduct.getSize() > 0) {
                try {
                    File saveFile = new ClassPathResource("static/images").getFile();
                    String newImageFile = UUID.randomUUID() + ".png";
                    Path path =  Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                    Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    nhanvien.setPhai(newImageFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            nhanvienServices.addNhanvien(nhanvien);
            return "redirect:/nhanviens";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanvien(@PathVariable("id") Long id){
        nhanvienServices.deleteNhanvien(id);
        return "redirect:/nhanviens";
    }
    @GetMapping("/edit/{id}")
    public String editNhanvienForm(@PathVariable("id") Long id,Model model){
        Nhanvien editNhanvien = nhanvienServices.getNhanvienId(id);
        if(editNhanvien!=null){
            model.addAttribute("nhanvien",editNhanvien);
            model.addAttribute("phongbans",phongbanService.getAllPhongbans());
            return "nhanvien/edit";
        }
        else{
            return "redirect:/nhanviens";
        }
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, @ModelAttribute("nhanvien") Nhanvien editNhanvien,@RequestParam MultipartFile imageProduct, BindingResult result, Model model){
        // check lỗi
        if(result.hasErrors()){
            model.addAttribute("categories",phongbanService.getAllPhongbans());
            return "nhanvien/edit";
        }
        if(imageProduct!=null&&imageProduct.getSize()>0){
            try {
                File savefile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(savefile.getAbsolutePath()+File.separator+editNhanvien.getPhai());
                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Nhanvien existingNhanvien = nhanvienServices.getNhanvienId(id);
            if (existingNhanvien != null){
                existingNhanvien.setPhai(editNhanvien.getPhai());
                existingNhanvien.setMa_nv(editNhanvien.getMa_nv());
                existingNhanvien.setTen_nv(editNhanvien.getTen_nv());
                existingNhanvien.setNoi_sinh(editNhanvien.getNoi_sinh());
                existingNhanvien.setPhongban(editNhanvien.getPhongban());
                existingNhanvien.setLuong(editNhanvien.getLuong());
                nhanvienServices.updateNhanvien(existingNhanvien);
            }
            return "redirect:/nhanviens";
        }
        return "redirect:/nhanviens";
    }
}
