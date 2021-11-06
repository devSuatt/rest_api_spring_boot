package com.devsuatt.backend.shared;

// Bu class ile kullanıcıya yaptığı işlemlerin sonucu olarak kayıt başarılı/başarısız vb.
// mesajlar göndermektir.

import org.json.simple.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class GenericResponse{
    private String message;

    public GenericResponse(String message) {
        this.message = message;
    }
}
