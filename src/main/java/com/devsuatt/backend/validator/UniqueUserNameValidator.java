package com.devsuatt.backend.validator;

import com.devsuatt.backend.service.IUserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private final IUserService userService;

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return !userService.isExistUserByUserName(userName);
    }
}
