package com.example.Buoi7_KT.validator;

import com.example.Buoi7_KT.entity.User;
import com.example.Buoi7_KT.validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null)
            return true;
        return user.getId()!=null;
    }
}