package com.devsuatt.backend.dto;

// @Unique gibi validation işlemlerini yapmak Entity(model) katmanında yapılırsa SOLID'in
// Single Resp. prensibine ters oluyor. Bir class tek bir işi yapmalıdır.

import com.devsuatt.backend.validator.UniqueUserName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateDTO {
    // Burada user modelimizdeki tüm alanlar değil de kullanıcıların hangi verileri
    // göndermesini istiyorsak onları yazmalıyız.

    @NotNull(message = "{backend.constraints.userName.NotNull.message}")
    @Size(min = 4, max = 24, message = "{backend.constraints.username.Size.message}")
    @UniqueUserName
    private String userName;

    @NotNull(message = "{backend.constraints.firstname.NotNull.message}")
    @Size(min = 2, max = 32, message = "{backend.constraints.firstname.Size.message}")
    private String firstName;

    @NotNull(message = "{backend.constraints.lastname.NotNull.message}")
    @Size(min = 3, max = 32, message = "{backend.constraints.lastname.Size.message}")
    private String lastName;

}
