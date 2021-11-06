package com.devsuatt.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserUpdateDTO {
    // update işlemleri için UserCreateDTO'yu da kullanabilirdik fakat bu DTO'da
    // userName gibi güncellenmemesi gereken field'ların olabileceği ihtimaline karşın
    // update işlemine özel bir DTO'ya ihtiyacımız var.

    @NotNull(message = "{backend.constraints.firstname.NotNull.message}")
    @Size(min = 2, max = 32, message = "{backend.constraints.firstname.Size.message}")
    private String firstName;

    @NotNull(message = "{backend.constraints.lastname.NotNull.message}")
    @Size(min = 3, max = 32, message = "{backend.constraints.lastname.Size.message}")
    private String lastName;

}
