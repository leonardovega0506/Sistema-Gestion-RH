package mx.com.snreh.dto;

import lombok.Data;

@Data
public class RegistrarUsuarioDTO {
    private String nombre;
    private String username;
    private String email;
    private String password;
}
