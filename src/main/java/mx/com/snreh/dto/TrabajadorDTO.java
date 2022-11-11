package mx.com.snreh.dto;

import lombok.Data;
import mx.com.snreh.model.TareaModel;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class TrabajadorDTO {

    private Long id;

    @NotEmpty
    @Size(min=2,message = "El numero del trabajador debe de contener al menos 2 caracteres")
    private Long numero_trabajador;

    @NotEmpty
    @Size(min = 2, message = "El nombre debe contener al menos 2 caracteres")
    private String nombre_trabajador;

    @NotEmpty
    @Size(min = 2, message = "El apellido paterno debe contener al menos 2 caracteres")
    private String apellidoP_trabajador;

    @NotEmpty
    @Size(min = 2,message = "El apellido materno debe de contener al menos 2 caracteres")
    private String apellidoM_trabajador;

    @NotEmpty
    private double sueldo;

    @NotEmpty
    @Size(min = 2,message = "El estatus debe de existir")
    private String estatus;


    private String celular;
    private String correo_electronico;

    @NotEmpty
    private String puesto;



}
