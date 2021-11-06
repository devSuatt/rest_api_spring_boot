package com.devsuatt.backend.validator;

import com.devsuatt.backend.repository.IUserRepository;
import com.devsuatt.backend.service.IUserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@RequiredArgsConstructor
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private final IUserService userService;

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return !userService.isExistUserByUserName(userName);
    }
}
