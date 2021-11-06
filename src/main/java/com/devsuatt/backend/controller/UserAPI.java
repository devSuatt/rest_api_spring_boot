package com.devsuatt.backend.controller;

import com.devsuatt.backend.dto.UserCreateDTO;
import com.devsuatt.backend.dto.UserUpdateDTO;
import com.devsuatt.backend.dto.UserViewDTO;
import com.devsuatt.backend.service.IUserService;
import com.devsuatt.backend.shared.GenericResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
// Bu class'ın rest service olduğunu ifade etmemiz için RestController annotiation
// ile işaretlemeliyiz. Web sayfamızdan gelecek bütün isteklerin burada
// karşılanmasını sağlamış olacağız.
@RequestMapping("/api")
// Bu class'taki tüm request içeren metotlarda, url'in nasıl başlayacağını
// ifade ederiz : localhost/api/...
public class UserAPI {

    // burada user bilgilerini çekmek için constructor injection sayesinde
    // servis katmanı ile haberleşmeliyiz. Ama direk user'a ait servisi değil de
    // onun interface'i ile haberleşmeliyiz. Soyutlarla (katmanlı mimari) çalışmak
    // bizi ileriki zamanlarda günün sonunda karlı hale getirecek.

    private final IUserService userService;

    public UserAPI(IUserService userService) {
        this.userService = userService;
    }

    // version güncellemesi yapılıp v2'ye geçilirse bu metodun başına
    // @Deprecated annotiation'u konulabilir.
    @GetMapping("v1/user/{id}")
    // Kullanıcıların request'ini hangi path ile karşılayacağımızı belirtiyoruz.
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable Long id) {
        // @PathVariable annotiation ile api path'i içerisinde
        // değişecek olan alanın id olduğunu belirttik: api/v1/user/{id}
        final UserViewDTO user = userService.getUserById(id);
        // Bu metotta kullanıcılara http status'leri ile JSON formatında
        // sonuç dönebileceğimiz bir class olan ResponseEntity nesnesi döndürdük.
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public String getHomePage() {
        return "HOME PAGE";
    }

    @GetMapping("v1/users")
    public ResponseEntity<List<UserViewDTO>> getUsers() {
        final List<UserViewDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("v1/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        userService.createUser(userCreateDTO);
        return ResponseEntity.ok(new GenericResponse("User created"));
    }

    @PutMapping("v1/user/{id}")
    public ResponseEntity<UserViewDTO> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        final UserViewDTO user = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("v1/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new GenericResponse("user deleted."));
    }

    // More Performance
    @GetMapping("v1/user/slice")
    public ResponseEntity<List<UserViewDTO>> slice(Pageable pageable) {
        final List<UserViewDTO> users = userService.slice(pageable);
        return ResponseEntity.ok(users);
    }

}
