package mx.com.snreh.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TrabajadorDTO {

    private Long id;

    @NotEmpty(message = "El numero del trabajador no puede estar vacio")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres el numero de trabajador")
    private Long numeroTrabajador;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 2,message = "Debe de tener al menos 2 caracteres el nombre del trabajador")
    private String nombre_trabajador;

    @NotEmpty(message = "El apellido del trabajador no puede estar vacio")
    private String apellidoP_trabajador;

    @NotEmpty(message = "El apellido del trabajador no puede estar vacio")
    private String apellido_Mtrabajador;

    @NotEmpty(message = "Debe de tener un sueldo asignado")
    private double sueldo;

    @NotEmpty(message = "Debe de tener un estatus valido")
    private String estatus;

    @Size(min = 10,max = 12,message = "El celular debe contener minimo 10 caracteres y maximo 12")
    private String celular;

    @NotEmpty(message = "Se le debe asignar un email al trabajador")
    @Email
    private String correo_electronico;

    @NotEmpty(message = "Debe de tener un puesto el trabajador")
    private String puesto;
}
