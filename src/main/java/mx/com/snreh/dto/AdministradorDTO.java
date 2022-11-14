package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AdministradorDTO {


    private int id_administrador;

    @NotEmpty(message = "No puede estar vacio el usuario")
    @Size(min = 10, message = "El Usuaurio debe tener al menos 10 caracteres")
    private String usuario;

    @NotEmpty(message = "No debe estar vacio el nombre")
    private String nombre;
}
