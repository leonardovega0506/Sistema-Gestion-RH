
package mx.com.snreh.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class TareaDTO {

    private long id_Tarea;

    @NotEmpty(message = "El nombre de la tarea no puede ser vacio")
    private String nombre;

    @NotEmpty(message = "Debe de tener una Fecha")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"M/d/dd","dd.MM.yyyy"})
    private Date fecha;

    @NotEmpty(message = "El estado no debe ser vacio o nulo")
    private String estatus;


}