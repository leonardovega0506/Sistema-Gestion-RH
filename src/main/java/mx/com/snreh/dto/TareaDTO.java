package mx.com.snreh.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Date;

@Data
public class TareaDTO {

    private long id;

    @NotEmpty
    @Size(min = 10, message = "Debe contener al menos 10 caracteres la tarea")
    private String nombre;

    @NotEmpty
    private Date fecha;

    @NotEmpty
    private String estatus;

}
