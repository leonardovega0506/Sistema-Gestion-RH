package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GerenteDTO {

    private long id_gerente;

    @NotEmpty(message = "No puede estar vacio el numero de gerente")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres")
    private long numero_gerente;

    @NotEmpty(message = "No puede estar vacio el nombre del gerente")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres")
    private String nombre_gerente;


    @NotEmpty(message = "No puede estar vacio el apellido del gerente")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres")
    private String apellidoP_gerente;


    @NotEmpty(message = "No puede estar vacio el apellido del gerente")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres")
    private String apllidoM_gerente;

    @NotEmpty(message = "Debe tener un sueldo el gerente")
    private double sueldo;

    @NotEmpty(message = "Debe de tener un estado el gerente")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres")
    private String estaus;

    @Size(min = 10, max = 12, message = "El celular debe de tener minimo 10 caracteres y maximo 12")
    private String celular;

    @NotEmpty(message = "No puede estar vacio el email del gerente")
    @Email
    private String correo_electronico;

    @NotEmpty(message = "Debe de tener un puesto asignado")
    private String puesto;
}
