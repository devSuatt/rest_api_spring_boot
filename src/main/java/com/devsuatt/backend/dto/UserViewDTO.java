package com.devsuatt.backend.dto;

import com.devsuatt.backend.model.User;
import lombok.Getter;

import java.io.Serializable;

// Sadece kullanıcıya gösterilmesini istediğimiz dataları burada belirtiyoruz.
// Gösterilmesini istemediğimiz alanları (id, isAdmin, password vs.)
// Entity (model) katmanında yazıyoruz.

// Lombok sayesinde Getter annotiation yazarak getter metodu yazmadık.
@Getter
public final class UserViewDTO implements Serializable {
// final class'lar inherit edilemez, bu class'ı bu amaçla final yazdık.
    private static final long serialVersionUID = 1L;

    // setter ile atama yapılmaması için final olarak tanımladık
    // bunlar final olduğu için bir constructor tanımlamamız gerekti.
    private final String firstName;
    private final String lastName;

    // mecburen oluşturduğumuz constructor'ın dışarıya açılmasını istemedik.
    // constructor private olduğundan, bu class'ın hiçbir anlamı kalmamış oluyor.
    // bundan dolayı class'ın davranışının kullanılacağı bir of metodu oluşturduk.
    private UserViewDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // işte burada sadece dışarıya açılmasını istediğimiz alanları
    // User class'ı yerine UserViewDTO ile vereceğiz.
    public static UserViewDTO of(User user) {
        return new UserViewDTO(user.getFirstName(), user.getLastName());
    }

}

