package com.example.Buoi7_KT.validator;

import com.example.Buoi7_KT.entity.Phongban;
import com.example.Buoi7_KT.validator.annotation.ValidPhongbanId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhongbanIdValidator implements ConstraintValidator<ValidPhongbanId, Phongban> {
    @Override
    public boolean isValid(Phongban phongban, ConstraintValidatorContext context){
        return phongban !=null && phongban.getMa_phong() !=null;
    }

}
