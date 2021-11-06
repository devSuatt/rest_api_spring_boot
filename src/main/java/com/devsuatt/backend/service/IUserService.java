package com.devsuatt.backend.service;

// Burası business katmanıdır. İş kontrollerimizi burada yaparız.
// model'lerimizle repository'lerimiz haberleşmeli, controller katmanı ile de
// service (business) katmanı haberleşmelidir.

import com.devsuatt.backend.dto.UserCreateDTO;
import com.devsuatt.backend.dto.UserUpdateDTO;
import com.devsuatt.backend.dto.UserViewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    // Burada Entity(User modeli) dönmek yerine verilerimizi sadece istediğimiz
    // field'ları dışarı açmak için, nesnelerimizi kısıtlanmış şekilde
    // dışarı gönderebilmek için UserViewDTO döndürüyoruz.
    UserViewDTO getUserById(Long Id);

    List<UserViewDTO> getUsers();

    UserViewDTO createUser(UserCreateDTO userCreateDTO);

    UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    void deleteUser(Long id);

    List<UserViewDTO> slice(Pageable pageable);

    boolean isExistUserByUserName(String userName);
}
