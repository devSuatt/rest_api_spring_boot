package com.devsuatt.backend.service;

import com.devsuatt.backend.dto.UserCreateDTO;
import com.devsuatt.backend.dto.UserUpdateDTO;
import com.devsuatt.backend.dto.UserViewDTO;
import com.devsuatt.backend.exception.UserNotFoundException;
import com.devsuatt.backend.model.User;
import com.devsuatt.backend.repository.IUserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Buranın bir servis olduğunu belirtiyoruz.
@Service
public class UserManager implements IUserService {

    // Repository katmanını buraya bağlamalıyız,
    // onunla servis katmanında haberleşmeliyiz.
    // repository katmanından db işlemleriyle ilgili metotları kullanacağız
    private final IUserRepository userRepository;

    public UserManager(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    // Bir metodun belirli bir iş kümesini sırasıyla gerçekleştirmesini garanti etmek
    // isteyebiliriz, bu tür durumlarda Transactional anotasyonunu kullanırız.
    // veritabanında herhangi bir değişiklik yapılmayacağı transactionlarda readOnly kullanılır
    // Propagation.SUPPORTS:Eğer bir transaction var ise o transaction u kullanır,
    // yok ise transaction’sız çalışır. Yeni bir transaction da açmaz
    public List<UserViewDTO> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserViewDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public UserViewDTO getUserById(Long id) {
        // orElseThrow metodu Java 10 ile gelen metottur, "eğer hata varsa..."
        // anlamına gelir.
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return UserViewDTO.of(user);
        // burada direk User tipinde nesne dönmek yerine user nesnesinin kısıtlanmış
        // halinin göndermek için UserViewDTO'nun of metodunu kullandık.
    }

    @Override
    @Transactional
    // @Transactional keywordu yazıldığında davranışı default olarak “REQUIRED” dır.
    // Propagation.REQUIRED: .Eğer mevcutta bir transaction var ise
    // yeni bir transaction açmadan bu transactionu kullanır,
    // Eğer transaction yoksa yeni bir transaction açar.
    public UserViewDTO createUser(UserCreateDTO userCreateDTO) {
        final User user = userRepository.save
                (new User(userCreateDTO.getUserName(),
                        userCreateDTO.getFirstName(),
                        userCreateDTO.getLastName()));
        return UserViewDTO.of(user);
    }

    @Override
    @Transactional
    public UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        final User updatedUser = userRepository.save(user);
        // update olan User nesnesini UserViewDTO nesnesine dönüştürüyoruz.
        return UserViewDTO.of(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> slice(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .stream()
                .map(UserViewDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExistUserByUserName(String userName) {
        return userRepository.existsUserByUsername(userName);
    }
}
